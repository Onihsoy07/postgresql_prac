package com.test.postgresql_test.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersJoinDto {

    @NotBlank(message = "username이 없습니다.")
    @Size(min = 3, max = 20, message = "username 길이 문제")
    private String username;

    @NotBlank(message = "password가 없습니다.")
    @Size(min = 3, max = 20, message = "password 길이 문제")
    private String password;

    private String email;

}
