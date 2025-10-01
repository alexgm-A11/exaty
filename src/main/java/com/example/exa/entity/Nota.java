package com.example.exa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name="TBL_NOTAS")
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_NOTA")
    private Long idnota;
    @Column(name = "NOTA",  nullable = false, length = 50)
    private String nota;
    @ManyToOne
    @JoinColumn(name = "ID_ESTUDIANTE", nullable = false)
    private Estudiante estudiante;
    @ManyToOne
    @JoinColumn(name = "ID_MATERIA",  nullable = false)
    private Materia materia;
}
