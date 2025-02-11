package com.users.dto;

import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Data
@Builder
public class UserDto {
    private String id;
    private String username;
    private String email;
}
