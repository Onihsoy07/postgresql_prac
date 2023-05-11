package com.test.postgresql_test.domain.dto.CfrResponseDtoInner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Info {
    public Size size;
    public Integer faceCount;
}
