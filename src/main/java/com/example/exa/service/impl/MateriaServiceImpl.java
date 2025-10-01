package com.example.exa.service.impl;

import com.example.exa.controller.exceptions.ResourceNotFoundException;
import com.example.exa.dto.MateriaDTO;
import com.example.exa.entity.Materia;
import com.example.exa.mappers.MateriaMapper;
import com.example.exa.repository.MateriaRepository;
import com.example.exa.service.service.MateriaService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaServiceImpl implements MateriaService {
    private final MateriaRepository materiaRepository;
    private final MateriaMapper materiaMapper;
    public MateriaServiceImpl(MateriaRepository materiaRepository, MateriaMapper materiaMapper) {
        this.materiaRepository = materiaRepository;
        this.materiaMapper = materiaMapper;
    }

    @Override
    public MateriaDTO create(MateriaDTO materiaDTO) throws ServiceException {
        try {
            Materia materia = materiaMapper.toEntity(materiaDTO);
            Materia materiaSaved = materiaRepository.save(materia);
            return materiaMapper.toDTO(materiaSaved);
        } catch (Exception e) {
            throw new ServiceException("Error al crear la materia",e);
        }
    }

    @Override
    public MateriaDTO update(Long aLong, MateriaDTO materiaDTO) throws ServiceException {
        try {
            Materia materia1 = materiaRepository.findById(aLong).orElseThrow(() -> new ServiceException("No existe la materia"));
            materia1.setMateria(materiaDTO.getMateria());
            return materiaMapper.toDTO(materiaRepository.save(materia1));
        } catch (ResourceNotFoundException e) {
            throw (e);
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar la materia: "+e);
        }
    }

    @Override
    public MateriaDTO findById(Long aLong) throws ServiceException {
        try {
            Materia mate =  materiaRepository.findById(aLong).orElseThrow(() -> new ServiceException("No existe la materia"));
            return materiaMapper.toDTO(mate);
        }catch (ResourceNotFoundException e) {
            throw (e);
        } catch (Exception e) {
            throw new ServiceException("Error al leer la materia con id " + aLong, e);
        }
    }

    @Override
    public void deleteById(Long aLong) throws ServiceException {
        try {
            if(!materiaRepository.findById(aLong).isPresent()){
                throw new ServiceException("No existe la materia");
            }
            materiaRepository.deleteById(aLong);
        }catch (ResourceNotFoundException e) {
            throw (e);
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar la materia con id " + aLong, e);
        }
    }

    @Override
    public List<MateriaDTO> findAll() throws ServiceException {
        try {
            return materiaMapper.toDTOs(materiaRepository.findAll());
        }catch (ResourceNotFoundException e) {
            throw (e);
        } catch (Exception e) {
            throw new ServiceException("Error al listar las materias " + e);
        }
    }
    }

