package com.example.exa.service.service;

import com.example.exa.dto.EstudianteDTO;
import com.example.exa.dto.NotaDTO;
import com.example.exa.entity.Estudiante;
import com.example.exa.service.base.GenericService;
import org.hibernate.service.spi.ServiceException;

import java.util.List;

public interface EstudianteService extends GenericService<Estudiante, EstudianteDTO, Long> {
    List<NotaDTO> findNotasByEstudiante(Long idEstudiante) throws ServiceException;
}

