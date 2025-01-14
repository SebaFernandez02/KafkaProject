package com.hiberus.cursos.consultadorclases.kafka.mapper;

public interface Mapper<K, V , D> {

    D toDTO(K key, V value);

}
