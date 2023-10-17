package com.test.postgresql_test.domain.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.jsonwebtoken.Claims;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
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

    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<CfrData> cfrDataList;

    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Board> boardList;

//    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private List<Reply> replyList;

//    private Set<? extends GrantedAuthority> authorities;


//    public Users(String username, String password, Set<? extends GrantedAuthority> authorities) {
//        this.username = username;
//        this.password = password;
//        this.authorities = authorities;
//    }

//    public User(String username, String password, boolean enabled, boolean accountNonExpired,
//                boolean credentialsNonExpired, boolean accountNonLocked,
//                Collection<? extends GrantedAuthority> authorities) {
//        Assert.isTrue(username != null && !"".equals(username) && password != null,
//                "Cannot pass null or empty values to constructor");
//        this.username = username;
//        this.password = password;
//        this.enabled = enabled;
//        this.accountNonExpired = accountNonExpired;
//        this.credentialsNonExpired = credentialsNonExpired;
//        this.accountNonLocked = accountNonLocked;
//        this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
//    }
}
