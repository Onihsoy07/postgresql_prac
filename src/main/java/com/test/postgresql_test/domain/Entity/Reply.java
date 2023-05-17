package com.test.postgresql_test.domain.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(schema = "postgresql_test")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String comment;

    private Long level;

    @ManyToOne(fetch = FetchType.EAGER)
    private Board board;

    @ManyToOne(fetch = FetchType.EAGER)
    private Users users;

}
