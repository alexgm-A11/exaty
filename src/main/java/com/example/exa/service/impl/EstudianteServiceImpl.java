package com.example.exa.service.impl;

import com.example.exa.controller.exceptions.ResourceNotFoundException;
import com.example.exa.dto.EstudianteDTO;
import com.example.exa.dto.NotaDTO;
import com.example.exa.entity.Estudiante;
import com.example.exa.entity.Nota;
import com.example.exa.mappers.EstudianteMapper;
import com.example.exa.mappers.NotaMapper;
import com.example.exa.repository.EstudianteRepository;
import com.example.exa.repository.NotaRepository;
import com.example.exa.service.service.EstudianteService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class EstudianteServiceImpl implements EstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final EstudianteMapper estudianteMapper;
    private final NotaRepository notaRepository;
    private final NotaMapper notaMapper;

    public EstudianteServiceImpl(EstudianteRepository estudianteRepository,
                                 EstudianteMapper estudianteMapper,
                                 NotaRepository notaRepository,
                                 NotaMapper notaMapper) {
        this.estudianteRepository = estudianteRepository;
        this.estudianteMapper = estudianteMapper;
        this.notaRepository = notaRepository;
        this.notaMapper = notaMapper;
    }

    @Override
    public EstudianteDTO create(EstudianteDTO estudianteDTO) throws ServiceException {
        try {
            Estudiante entity = estudianteMapper.toEntity(estudianteDTO);

            // Arreglar la relación bidireccional después del mapeo
            if (entity.getNotas() != null && !entity.getNotas().isEmpty()) {
                List<Nota> notas = new ArrayList<>(entity.getNotas());
                entity.getNotas().clear(); // Limpiar la lista

                // Volver a agregar usando el método helper
                for (Nota nota : notas) {
                    entity.addNota(nota);
                }
            }

            Estudiante saved = estudianteRepository.save(entity);
            return estudianteMapper.toDTO(saved);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("Error al crear el estudiante: " + e.getMessage(), e);
        }
    }

    @Override
    public EstudianteDTO update(Long id, EstudianteDTO estudianteDTO) throws ServiceException {
        try {
            Estudiante estudianteExistente = estudianteRepository.findById(id)
                    .orElseThrow(() -> new ServiceException("No existe el estudiante con id " + id));

            estudianteExistente.setNombre(estudianteDTO.getNombre());
            estudianteExistente.setApellidos(estudianteDTO.getApellidos());
            estudianteExistente.setDireccion(estudianteDTO.getDireccion());
            estudianteExistente.setTelefono(estudianteDTO.getTelefono());

            Estudiante actualizado = estudianteRepository.save(estudianteExistente);
            return estudianteMapper.toDTO(actualizado);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("Error al actualizar el estudiante con id " + id + ": " + e.getMessage(), e);
        }
    }

    @Override
    public EstudianteDTO findById(Long id) throws ServiceException {
        try {
            Estudiante estudiante = estudianteRepository.findById(id)
                    .orElseThrow(() -> new ServiceException("No existe el estudiante con id " + id));
            return estudianteMapper.toDTO(estudiante);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("Error al leer el estudiante con id " + id + ": " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        try {
            if (!estudianteRepository.existsById(id)) {
                throw new ServiceException("No existe el estudiante con id " + id);
            }
            estudianteRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("Error al eliminar el estudiante con id " + id + ": " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<EstudianteDTO> findAll() throws ServiceException {
        try {
            List<Estudiante> estudiantes = estudianteRepository.findAll();
            return estudianteMapper.toDTOs(estudiantes);
        } catch (Exception e) {
            e.printStackTrace(); // 🔎 Mostrará el error real en la consola
            throw new ServiceException("Error al listar los estudiantes: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotaDTO> findNotasByEstudiante(Long idEstudiante) throws ServiceException {
        try {
            if (!estudianteRepository.existsById(idEstudiante)) {
                throw new ServiceException("No existe el estudiante con id " + idEstudiante);
            }

            return notaRepository.findByEstudianteId(idEstudiante)
                    .stream()
                    .map(notaMapper::toDTO)
                    .toList();

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("Error al listar las notas del estudiante con id " + idEstudiante + ": " + e.getMessage(), e);
        }
    }
}