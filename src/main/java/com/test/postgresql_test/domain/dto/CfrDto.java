package com.test.postgresql_test.domain.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.test.postgresql_test.domain.Entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CfrDto {
    private Long id;

    private String value;

    private Float confidence;

    private Users users;
}
