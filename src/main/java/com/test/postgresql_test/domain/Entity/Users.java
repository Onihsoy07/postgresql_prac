package com.test.postgresql_test.domain.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Users extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = true, unique = false)
    private String email;

    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<CfrData> cfrDataList;

    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("users")
    private Set<Board> boardList;

//    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private List<Reply> replyList;

}
