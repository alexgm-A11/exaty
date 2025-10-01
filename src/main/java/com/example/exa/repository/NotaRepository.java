package com.example.exa.repository;

import com.example.exa.entity.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotaRepository extends JpaRepository<Nota,Long> {
    List<Nota> findByEstudianteId(Long idestudiante);
}
