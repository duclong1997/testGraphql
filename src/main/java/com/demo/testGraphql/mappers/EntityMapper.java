package com.demo.testGraphql.mappers;

import java.util.List;

public interface EntityMapper<D, E> {
    E dtoToEntity(D dto);

    D entityToDto(E entity);

    List<E> dtosToEntities(List<D> dtos);

    List<D> entitiesToDtos(List<E> entities);
}
