package com.users.model;


import com.users.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    public User toEntity(UserDto user) {
        return User.builder()
                .id(UUID.fromString(user.getId()))
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    public UserDto toDto() {
        return UserDto.builder()
                .id(id.toString())
                .username(username)
                .email(email)
                .build();
    }
}
