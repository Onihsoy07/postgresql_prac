package com.test.postgresql_test.domain.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(schema = "postgresql_test")
public class CfrData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, unique = false)
    private String value;

    @Column(nullable = false, unique = false)
    private Float confidence;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users")
    private Users users;

    //{"info":
//  {
//      "size":{"width":120,"height":160},
//      "faceCount":1
//  },
//"faces":
// [{
//  "celebrity":{"value":"전진우","confidence":1.0}
//  }]
//}
}
