package com.users.service.mapper;

public interface Mapper<T, D> {
    T dtoToEntity(D dto);
}
