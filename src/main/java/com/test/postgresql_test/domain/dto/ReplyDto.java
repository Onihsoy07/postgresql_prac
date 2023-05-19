package com.test.postgresql_test.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.Access;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReplyDto {

    private String comment;
}
