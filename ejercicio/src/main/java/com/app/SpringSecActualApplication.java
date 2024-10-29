package com.app;


import com.app.persistence.EstadoEntity;
import com.app.persistence.RoleEntity;
import com.app.persistence.UserEntity;
import com.app.repository.EstadoRepository;
import com.app.repository.RoleRepository;
import com.app.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class SpringSecActualApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringSecActualApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataLoder(UserRepository userRepository, RoleRepository roleRepository, EstadoRepository estadoRepository, PasswordEncoder passwordEncoder){
        return args -> {

            //ESTADOS

            EstadoEntity en_curso = EstadoEntity.builder()
                    .nombre("EN_CURSO")
                    .build();

            EstadoEntity programada = EstadoEntity.builder()
                    .nombre("PROGRAMADA")
                    .build();

            EstadoEntity aplazada = EstadoEntity.builder()
                    .nombre("APLAZADA")
                    .build();

            EstadoEntity cancelada = EstadoEntity.builder()
                    .nombre("CANCELADA")
                    .build();

            estadoRepository.saveAll(Set.of(en_curso,programada,aplazada,cancelada));

            //ROLES

            RoleEntity docenteRole = RoleEntity.builder()
                    .nombre("DOCENTE")
                    .build();

            RoleEntity rectorRole = RoleEntity.builder()
                    .nombre("RECTOR")
                    .build();

            RoleEntity estudianteRole = RoleEntity.builder()
                    .nombre("ESTUDIANTE")
                    .build();


            //USUARIOS

            UserEntity rector = UserEntity.builder()
                    .username("rector")
                    .nombre("Liang Camilo")
                    .apellido("Alvarez Tierradentro")
                    .email("liangcamilo2006@gmail.com")
                    .nit(1076905175)
                    .direccion("Calle 5b # 21-04")
                    .roles(Set.of(rectorRole))
                    .password(passwordEncoder.encode("1234"))
                    .build();

            UserEntity estudiante = UserEntity.builder()
                    .username("estudiante")
                    .nombre("Nicolle Kaleth")
                    .apellido("Nathalia")
                    .email("kaleth@gmail.com")
                    .nit(1122334455)
                    .direccion("Usco")
                    .roles(Set.of(estudianteRole))
                    .password(passwordEncoder.encode("1234"))
                    .build();

            UserEntity docente = UserEntity.builder()
                    .username("docente")
                    .nombre("Profesor")
                    .apellido("Example")
                    .email("profesor@email.com")
                    .nit(12345678)
                    .direccion("Dirección Ejemplo")
                    .roles(Set.of(docenteRole))
                    .password(passwordEncoder.encode("1234"))
                    .build();


            userRepository.saveAll(Set.of(rector,estudiante,docente));

        };



    }


}


