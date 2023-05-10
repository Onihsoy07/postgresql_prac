package com.test.postgresql_test.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CfrResponseDto {

    public Info info;
    public List<Face> faces;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Info {
        public Size size;
        public Integer faceCount;
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Size {
    public Integer width;
    public Integer height;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Face {
    public Celebrity celebrity;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Celebrity {
    public String value;
    public Float confidence;
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