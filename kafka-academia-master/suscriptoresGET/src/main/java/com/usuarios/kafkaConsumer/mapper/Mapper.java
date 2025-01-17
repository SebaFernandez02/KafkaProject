package com.usuarios.kafkaConsumer.mapper;

public interface Mapper <K, V , D>{
    D toDto(K key, V value);
}
