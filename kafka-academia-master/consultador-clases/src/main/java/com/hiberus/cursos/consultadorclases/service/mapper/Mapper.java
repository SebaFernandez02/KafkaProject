package com.hiberus.cursos.consultadorclases.service.mapper;

import java.util.List;

public interface Mapper<T, D> {

    D entityToDto(T entity);

    T dtoToEntity(D dto);

    List<D> entityListToDtoList(List<T> entities);

    List<T> dtoListToEntityList(List<D> dtos);
}
