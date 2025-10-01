package com.example.exa.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EstudianteDTO {
    private Long id;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String telefono;
    private List<NotaDTO> notas;
}
