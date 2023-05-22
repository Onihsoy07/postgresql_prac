package com.test.postgresql_test.domain.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.core.annotation.Order;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(schema = "postgresql_test")
public class CfrData extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, unique = false)
    private String value;

    @Column(nullable = false, unique = false)
    @OrderBy("createDate")
    private Float confidence;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
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
