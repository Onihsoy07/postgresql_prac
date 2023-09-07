package com.test.postgresql_test.domain.dto;

import com.test.postgresql_test.domain.Entity.Reply;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ReplyDto {

    private Long id;
    private String comment;
    private Integer level;

    public ReplyDto(Reply reply) {
        this.id = reply.getId();
        this.comment = reply.getComment();
        this.level = reply.getLevel();
    }

    public static List<ReplyDto> convertToReplyDtoList(Set<Reply> replyList) {
        return replyList.stream().map(ReplyDto::new).collect(Collectors.toList());
    }
}
