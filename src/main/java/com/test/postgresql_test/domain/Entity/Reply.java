package com.test.postgresql_test.domain.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Table(schema = "postgresql_test")
public class Reply extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String comment;

    private int level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Reply reply;

    @OneToMany(mappedBy = "reply", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Reply> replyList;
}
