package com.app.config;

import com.app.service.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;


@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception {

        http.authorizeHttpRequests( auth -> {
            //para visitar la doc: http://localhost:8080/swagger-ui/index.html
            auth.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll();
            auth.requestMatchers("/login", "/css/**", "/js/**").permitAll();
            auth.requestMatchers("/home").permitAll();
            auth.requestMatchers("/clases").hasAnyRole("ESTUDIANTE", "RECTOR", "DOCENTE");
            auth.requestMatchers("/clases/crear").hasRole("RECTOR");
            auth.requestMatchers("/clases/editar").hasRole("RECTOR");
                    auth.requestMatchers("/clases/editar/{id}").access(new WebExpressionAuthorizationManager(
                            "hasRole('RECTOR') or " +
                                    "(hasRole('DOCENTE') and @claseService.isDocenteOfClase(authentication.name, #id))"));


                    //Resto de endpoints
            auth.anyRequest().authenticated();


        })
                .formLogin( formLogin -> {
            formLogin.loginPage("/login");
            formLogin.loginProcessingUrl("/login");
            formLogin.usernameParameter("username");
            formLogin.passwordParameter("password");
            formLogin.failureUrl("/login?error");
            formLogin.successHandler(successHandler());
            formLogin.permitAll();
        })
                .logout(logout -> {
            logout.logoutUrl("/logout");
            logout.logoutSuccessUrl("/loginPage?logout");
            logout.invalidateHttpSession(true);
            logout.deleteCookies("JSESSIONID");
        }).csrf(AbstractHttpConfigurer::disable);



        return http.build();
    }


    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return (request, response, auth) -> {
            response.sendRedirect("/home");
        };
    }

    @Bean
    public SpringSecurityDialect securityDialect() {
        return new SpringSecurityDialect();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailService) throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }

}





