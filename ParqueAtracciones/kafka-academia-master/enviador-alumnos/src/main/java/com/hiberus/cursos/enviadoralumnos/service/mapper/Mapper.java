package com.hiberus.cursos.enviadoralumnos.service.mapper;

public interface Mapper<T, D> {

    T dtoToEntity(D dto);

}
