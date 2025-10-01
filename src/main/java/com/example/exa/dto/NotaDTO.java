package com.example.exa.dto;

import com.example.exa.entity.Estudiante;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotaDTO {
    private Long idnota;
    private String nota;
    private Long idEstudiante;
    private MateriaDTO materia;
}
