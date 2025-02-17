package com.users.service.mapper;

import com.usuarios.avro.UserValue;
import com.users.dto.UserDto;

public class UserValueMapper implements Mapper<UserValue, UserDto>{
    @Override
    public UserValue dtoToEntity(UserDto dto) {
        return UserValue.newBuilder()
                .setId(dto.getId().toString())
                .setUsername(dto.getUsername())
                .setEmail(dto.getEmail())
                .build();
    }
}
