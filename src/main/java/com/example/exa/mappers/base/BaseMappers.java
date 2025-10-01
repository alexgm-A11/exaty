package com.example.exa.mappers.base;

import java.util.List;

public interface BaseMappers<E, D> {
    D toDTO(E entity);
    E toEntity(D dto);
    List<D> toDTOs(List<E> entities);
    List<E> toEntities(List<D> dtos);
}