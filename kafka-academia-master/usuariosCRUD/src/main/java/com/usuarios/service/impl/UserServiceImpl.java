package com.usuarios.service.impl;

import com.usuarios.avro.UserKey;
import com.usuarios.avro.UserValue;
import com.usuarios.dto.UserDto;
import com.usuarios.service.UserService;
import com.usuarios.service.mapper.UserKeyMapper;
import com.usuarios.service.mapper.UserValueMapper;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private String userTopic = "users";

    @Autowired
    private KafkaTemplate<UserKey, UserValue> kafkaTemplate;
    @Override
    public void createUser(UserDto user) {
        if (user == null || user.getUsername() == null || user.getEmail() == null){
            log.error("El usuario es nulo o esta incompleto");
            return;
        }
        user.setId(UUID.randomUUID());

        UserKey userKey = new UserKeyMapper().dtoToEntity(user);
        UserValue userValue = new UserValueMapper().dtoToEntity(user);

        log.debug("Enviando el user al topic de Kafka");
        kafkaTemplate.send(userTopic, userKey, userValue);
    }
}
