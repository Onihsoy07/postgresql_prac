package com.test.postgresql_test.domain.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsersLonginDto {

    private String id;
    private String password;
}
