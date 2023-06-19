package com.test.postgresql_test.controller.api;

import com.test.postgresql_test.Service.NaverCFRService;
import com.test.postgresql_test.Service.UsersService;
import com.test.postgresql_test.config.auth.PrincipalDetails;
import com.test.postgresql_test.domain.Entity.CfrData;
import com.test.postgresql_test.domain.dto.CfrResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class NaverCFRApiController {

    private final NaverCFRService naverCFRService;

    @PostMapping("/test/cfr")
    public ResponseEntity<CfrResponseDto> getCRF(@RequestParam("image") MultipartFile multipartFile,
                                                 @AuthenticationPrincipal final PrincipalDetails principal) throws Exception {
        CfrResponseDto cfrResponseDto = naverCFRService.getCFR(multipartFile);
        naverCFRService.save(cfrResponseDto, principal.getUsers());
        return ResponseEntity.status(HttpStatus.OK).body(cfrResponseDto);
    }

}
