package com.grupo07.notionary.converter;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractConverter<E, D> {
    public abstract D fromEntity(E entity);
    public abstract E fromDto(D dto);

    public List<D> fromEntities(List<E> entities) {
        return entities.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }

    public List<E> fromDtos(List<D> dtos) {
        return dtos.stream()
                .map(this::fromDto)
                .collect(Collectors.toList());
    }
}
