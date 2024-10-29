package com.app.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "estados")
public class EstadoEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true, name = "nombre")
    private String nombre;

}
