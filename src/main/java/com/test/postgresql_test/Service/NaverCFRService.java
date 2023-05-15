package com.test.postgresql_test.Service;

import com.test.postgresql_test.domain.Entity.CfrData;
import com.test.postgresql_test.domain.Entity.Users;
import com.test.postgresql_test.domain.dto.CfrResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface NaverCFRService {

    CfrResponseDto getCFR(MultipartFile multipartFile) throws Exception;

    void save(CfrResponseDto cfrResponseDto, Users users);

}
