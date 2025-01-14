package com.hiberus.cursos.enviadorprofesores.service.mapper;

public interface Mapper<T, D> {

    T dtoToEntity(D dto);

}
