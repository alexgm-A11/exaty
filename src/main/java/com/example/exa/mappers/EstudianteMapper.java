package com.example.exa.mappers;

import com.example.exa.dto.EstudianteDTO;
import com.example.exa.entity.Estudiante;
import com.example.exa.mappers.base.BaseMappers;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {NotaMapper.class})
public interface EstudianteMapper {
    EstudianteDTO toDTO(Estudiante entity);
    Estudiante toEntity(EstudianteDTO dto);

    List<EstudianteDTO> toDTOs(List<Estudiante> entities);
    List<Estudiante> toEntities(List<EstudianteDTO> dtos);
}
