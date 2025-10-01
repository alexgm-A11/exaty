package com.example.exa.service.impl;

import com.example.exa.controller.exceptions.ResourceNotFoundException;
import com.example.exa.dto.NotaDTO;
import com.example.exa.entity.Estudiante;
import com.example.exa.entity.Materia;
import com.example.exa.entity.Nota;
import com.example.exa.mappers.NotaMapper;
import com.example.exa.repository.NotaRepository;
import com.example.exa.service.service.NotaService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotaServiceImpl implements NotaService {
  private final NotaRepository notaRepository;
  private final NotaMapper notaMapper;
  public NotaServiceImpl(NotaRepository notaRepository, NotaMapper notaMapper) {
      this.notaRepository = notaRepository;
      this.notaMapper = notaMapper;
  }

    @Override
    public NotaDTO create(NotaDTO notaDTO) throws ServiceException {
        try {
            return notaMapper.toDTO(notaRepository.save( notaMapper.toEntity(notaDTO)));
        } catch (Exception e) {
            throw new RuntimeException("Error al crear la nota: "+e);
        }
    }

    @Override
    public NotaDTO update(Long aLong, NotaDTO notaDTO) throws ServiceException {
        try {
            Nota nota1 = notaRepository.findById(aLong).orElseThrow(() -> new ServiceException("No existe la nota"));
            nota1.setNota(notaDTO.getNota());
            nota1.setEstudiante(Estudiante.builder().build());
            nota1.setMateria(Materia.builder().build());
            return notaMapper.toDTO(notaRepository.save(nota1));
        } catch (ResourceNotFoundException e) {
            throw (e);
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar la nota: "+e);
        }
    }

    @Override
    public NotaDTO findById(Long aLong) throws ServiceException {
        try {
            Nota not =  notaRepository.findById(aLong).orElseThrow(() -> new ServiceException("No existe la nota"));
            return notaMapper.toDTO(not);
        }catch (ResourceNotFoundException e) {
            throw (e);
        } catch (Exception e) {
            throw new ServiceException("Error al leer la nota con id " + aLong, e);
        }
    }

    @Override
    public void deleteById(Long aLong) throws ServiceException {
        try {
            if(!notaRepository.findById(aLong).isPresent()){
                throw new ServiceException("No existe la nota");
            }
            notaRepository.deleteById(aLong);
        }catch (ResourceNotFoundException e) {
            throw (e);
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar la nota con id " + aLong, e);
        }
    }

    @Override
    public List<NotaDTO> findAll() throws ServiceException {
        try {
            return notaMapper.toDTOs(notaRepository.findAll());
        }catch (ResourceNotFoundException e) {
            throw (e);
        } catch (Exception e) {
            throw new ServiceException("Error al listar las notas " + e);
        }
    }
}
