package com.usuarios.service.mapper;

import com.usuarios.avro.UserValue;
import com.usuarios.dto.UserDto;

public class UserValueMapper implements Mapper<UserValue, UserDto>{
    @Override
    public UserValue dtoToEntity(UserDto dto) {
        return null;
    }
}
