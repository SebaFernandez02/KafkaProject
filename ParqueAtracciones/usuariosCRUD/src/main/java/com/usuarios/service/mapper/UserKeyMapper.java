package com.usuarios.service.mapper;

import com.usuarios.avro.UserKey;
import com.usuarios.dto.UserDto;

public class UserKeyMapper implements Mapper<UserKey, UserDto>{

    @Override
    public UserKey dtoToEntity(UserDto dto) {
        return UserKey.newBuilder().setId(dto.getId().toString()).build();
    }
}
