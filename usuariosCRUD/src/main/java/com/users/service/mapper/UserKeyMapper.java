package com.users.service.mapper;

import com.usuarios.avro.UserKey;
import com.users.dto.UserDto;

public class UserKeyMapper implements Mapper<UserKey, UserDto>{

    @Override
    public UserKey dtoToEntity(UserDto dto) {
        return UserKey.newBuilder()
                .setId(dto.getId().toString())
                .build();
    }
}
