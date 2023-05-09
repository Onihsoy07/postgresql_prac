package com.test.postgresql_test.controller;

import com.test.postgresql_test.Service.NaverCFRService;
import com.test.postgresql_test.domain.dto.CFRResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class NaverCFRApiController {

    private final NaverCFRService naverCFRService;

    @PostMapping("/test/crf")
    public ResponseEntity<String> getCRF(@RequestParam("image") MultipartFile multipartFile) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(naverCFRService.getCFR(multipartFile));
    }

    @PostMapping("/test")
    public ResponseEntity<?> test() {
        naverCFRService.getTest();
        return ResponseEntity.status(HttpStatus.OK).body(1353551);
    }

    @GetMapping({"/",""})
    public String tt() {
        return "<h1>hello</h1>";
    }

}
