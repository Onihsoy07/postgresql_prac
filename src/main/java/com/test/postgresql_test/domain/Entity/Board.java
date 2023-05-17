package com.test.postgresql_test.domain.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(schema = "postgresql_test")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    private Users users;

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Reply> replyList;
}
