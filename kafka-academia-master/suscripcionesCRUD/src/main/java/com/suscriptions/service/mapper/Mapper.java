package com.suscriptions.service.mapper;

public interface Mapper<T, D> {
    T dtoToEntity(D dto);
}
