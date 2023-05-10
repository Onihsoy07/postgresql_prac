package com.test.postgresql_test.Service;

import com.test.postgresql_test.domain.dto.CfrResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface NaverCFRService {

    CfrResponseDto getCFR(MultipartFile multipartFile) throws Exception;

}
