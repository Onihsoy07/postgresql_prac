package com.test.postgresql_test.domain.Entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class Team {

    @Id
    private Long id;
    private String teamName;
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "team")
    private List<Member> members;

    @Builder
    public Team(String teamName) {
        this.teamName = teamName;
    }
}
