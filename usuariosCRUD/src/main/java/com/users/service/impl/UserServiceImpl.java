package com.users.service.impl;

import com.usuarios.avro.UserKey;
import com.usuarios.avro.UserValue;
import com.users.dto.UserDto;
import com.users.service.UserService;
import com.users.service.mapper.UserKeyMapper;
import com.users.service.mapper.UserValueMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.security.InvalidParameterException;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private String userTopic = "users";

    @Autowired
    private KafkaTemplate<UserKey, UserValue> kafkaTemplate;
    @Override
    public void createUser(UserDto user) {
        if (user == null || user.getUsername().isBlank() || user.getEmail().isBlank()){
            log.error("El usuario es nulo o esta incompleto");
            throw new InvalidParameterException("Usuario nulo o incompleto");
        }
        user.setId(UUID.randomUUID());

        UserKey userKey = new UserKeyMapper().dtoToEntity(user);
        UserValue userValue = new UserValueMapper().dtoToEntity(user);

        log.debug("Enviando el user al topic de Kafka");
        kafkaTemplate.send(userTopic, userKey, userValue);
    }
}
