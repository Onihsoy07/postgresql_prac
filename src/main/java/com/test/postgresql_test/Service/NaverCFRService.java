package com.test.postgresql_test.Service;

import com.test.postgresql_test.domain.dto.CFRResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface NaverCFRService {

    CFRResponseDto getCFR(MultipartFile multipartFile) throws Exception;

}
