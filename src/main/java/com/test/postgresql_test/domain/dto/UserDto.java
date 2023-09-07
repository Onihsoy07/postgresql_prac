package com.test.postgresql_test.domain.dto;

import com.test.postgresql_test.domain.Entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;

    public UserDto(Users users) {
        this.id = users.getId();
        this.username = users.getUsername();
    }
}
