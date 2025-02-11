package com.users.service.impl;

import com.users.model.User;
import com.users.repository.UsuariosRepository;
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
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private String userTopic = "users";

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private KafkaTemplate<UserKey, UserValue> kafkaTemplate;
    @Override
    public void createUser(UserDto user) {
        if (user == null || user.getUsername().isBlank() || user.getEmail().isBlank()){
            log.error("El usuario es nulo o esta incompleto");
            throw new InvalidParameterException("Usuario nulo o incompleto");
        }
        if (!ensureUserNotExists(user.getEmail())) {
            log.error("El usuario ya existe");
            throw new InvalidParameterException("El usuario ya existe");
        }
        user.setId(UUID.randomUUID().toString());

        UserKey userKey = new UserKeyMapper().dtoToEntity(user);
        UserValue userValue = new UserValueMapper().dtoToEntity(user);

        log.debug("Enviando el user al topic de Kafka");
        kafkaTemplate.send(userTopic, userKey, userValue);
        User userEntity = new User();
        usuariosRepository.save(userEntity.toEntity(user));
    }

    @Override
    public List<UserDto> getUsers() {
        return usuariosRepository.findAll().stream().map(User::toDto).collect(Collectors.toList());
    }

    private boolean ensureUserNotExists(String email){
        return usuariosRepository.findByEmail(email) == null;
    }
}
