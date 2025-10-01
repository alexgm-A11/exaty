package com.example.exa.mappers;

import com.example.exa.dto.MateriaDTO;
import com.example.exa.entity.Materia;
import com.example.exa.mappers.base.BaseMappers;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MateriaMapper  {
    MateriaDTO toDTO(Materia entity);
    Materia toEntity(MateriaDTO dto);

    List<MateriaDTO> toDTOs(List<Materia> entities);

}
