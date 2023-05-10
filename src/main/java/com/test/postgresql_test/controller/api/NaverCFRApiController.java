package com.test.postgresql_test.controller.api;

import com.test.postgresql_test.Service.NaverCFRService;
import com.test.postgresql_test.domain.dto.CfrResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class NaverCFRApiController {

    private final NaverCFRService naverCFRService;

    @PostMapping("/test/cfr")
    public ResponseEntity<CfrResponseDto> getCRF(@RequestParam("image") MultipartFile multipartFile) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(naverCFRService.getCFR(multipartFile));
    }

//    @GetMapping("/test")
//    public ResponseEntity<Integer> tt() {
//        System.out.println("hello");
//        return ResponseEntity.status(HttpStatus.OK).body(1234);
//    }

}
