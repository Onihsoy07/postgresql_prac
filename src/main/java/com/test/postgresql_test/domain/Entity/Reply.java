package com.test.postgresql_test.domain.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
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
    @JsonBackReference
    private Board board;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Users users;

}
