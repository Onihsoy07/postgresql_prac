package com.test.postgresql_test.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.test.postgresql_test.domain.dto.CfrResponseDtoInner.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CfrResponseDto {

    public Info info;
    public List<Face> faces;

}


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