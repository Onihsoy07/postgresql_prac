package com.test.postgresql_test.domain.dto;

import com.test.postgresql_test.domain.Entity.Users;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardFullDto {

    private Long id;
    private String title;
    private String content;
    private int viewCount;
    private Users users;

    @Builder
    public BoardFullDto(Long id, String title, String content, int viewCount, Users users) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.users = users;
    }
}
