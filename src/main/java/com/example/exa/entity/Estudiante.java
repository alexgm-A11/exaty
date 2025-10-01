package com.example.exa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name="TBL_ESTUDIANTES")
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ESTUDIANTE")
    private Long id;

    @Column(name = "NOMBRE",  nullable = false, length = 50)
    private String nombre;

    @Column(name = "APELLIDOS", nullable = false, length = 50)
    private String apellidos;

    @Column(name = "DIRECCION",  nullable = false, length = 50)
    private String direccion;

    @Column(name = "TELEFONO",  nullable = false, length = 50)
    private String telefono;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Nota> notas = new ArrayList<>();

    // Métodos helper para mantener la relación bidireccional
    public void addNota(Nota nota) {
        notas.add(nota);
        nota.setEstudiante(this);
    }

    public void removeNota(Nota nota) {
        notas.remove(nota);
        nota.setEstudiante(null);
    }
}
