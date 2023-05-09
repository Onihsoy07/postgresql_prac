package com.test.postgresql_test.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CFRResponseDto {

    public Info info;
    public List<Face> faces;

    class Info {
        public Size size;
        public Integer faceCount;
        class Size {
            public Integer width;
            public Integer height;
        }
    }

    class Face {
        public Celebrity celebrity;
        class Celebrity {
            public String value;
            public Float confidence;
        }
    }
}
