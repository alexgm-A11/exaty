package com.example.exa.mappers;

import com.example.exa.dto.NotaDTO;
import com.example.exa.entity.Nota;
import com.example.exa.mappers.base.BaseMappers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotaMapper {

    @Mapping(source = "estudiante.id", target = "idEstudiante")
    NotaDTO toDTO(Nota entity);

    @Mapping(source = "idEstudiante", target = "estudiante.id")
    Nota toEntity(NotaDTO dto);

    List<NotaDTO> toDTOs(List<Nota> entities);

    List<Nota> toEntities(List<NotaDTO> dtos);
}
