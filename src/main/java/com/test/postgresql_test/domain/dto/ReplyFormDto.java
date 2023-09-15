package com.test.postgresql_test.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReplyFormDto {

    @NotBlank(message = "공백은 작성이 안됩니다.")
    private String comment;

    private Long replyId;
    private int depth;

}
