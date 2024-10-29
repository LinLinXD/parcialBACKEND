package com.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para la transferencia de datos de clase")
public class ClaseDTO {

    @Schema(description = "Nombre de la clase", example = "Matemáticas")
    private String nombre;

    @Schema(description = "Hora de inicio de la clase (formato 24h)", example = "8")
    private Integer horarioInicio;

    @Schema(description = "Hora de finalización de la clase (formato 24h)", example = "10")
    private Integer horarioFinal;

    @Schema(description = "Nombre del docente", example = "Juan Pérez")
    private String docente;

    @Schema(description = "Identificador del salón", example = "A-101")
    private String salon;

    @Schema(description = "Estado de la clase", example = "PROGRAMADA")
    private String estado;
}
