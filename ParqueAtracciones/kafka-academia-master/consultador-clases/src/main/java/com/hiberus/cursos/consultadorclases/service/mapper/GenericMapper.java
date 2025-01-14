package com.hiberus.cursos.consultadorclases.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

public abstract class GenericMapper<T,D> implements Mapper<T,D> {

    @Override
    public List<D> entityListToDtoList(List<T> entities) {
        return entities.stream().map(entity -> entityToDto(entity)).collect(Collectors.toList());
    }


    @Override
    public List<T> dtoListToEntityList(List<D> dtos) {
        return dtos.stream().map(dto -> dtoToEntity(dto)).collect(Collectors.toList());
    }
}
