package com.test.postgresql_test.Service.ServiceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.postgresql_test.Service.NaverCFRService;
import com.test.postgresql_test.domain.dto.CFRResponseDto;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

@Service
public class NaverCFRServiceImpl implements NaverCFRService {

    @Value("${open_api.naver.X_Naver_Client_Id}")
    private String X_Naver_Client_Id;

    @Value("${open_api.naver.X_Naver_Client_Secret}")
    private String X_Naver_Client_Secret;

//    [HTTP Request Header]
//    POST /v1/vision/celebrity HTTP/1.1
//    Host: openapi.naver.com
//    Content-Type: multipart/form-data; boundary={boundary-text}
//    X-Naver-Client-Id: {앱 등록 시 발급받은 Client ID}
//    X-Naver-Client-Secret: {앱 등록 시 발급 받은 Client Secret}
//    Content-Length: 96703
//
//    --{boundary-text}
//    Content-Disposition: form-data; name="image"; filename="test.jpg"
//    Content-Type: image/jpeg
//
//    {image binary data}
//    --{boundary-text}--

    @Override
    public CFRResponseDto getCFR(MultipartFile multipartFile) throws Exception {
        RestTemplate rt = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        httpHeaders.add("X-Naver-Client-Id", X_Naver_Client_Id);
        httpHeaders.add("X-Naver-Client-Secret", X_Naver_Client_Secret);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", multipartFile.getResource());

        HttpEntity<MultiValueMap<String, Object>> CFRRequest =
                new HttpEntity<>(body, httpHeaders);

        ResponseEntity<String> response = rt.exchange(
                "https://openapi.naver.com/v1/vision/celebrity",
                HttpMethod.POST,
                CFRRequest,
                String.class
        );

        ObjectMapper mapper = new ObjectMapper();
        CFRResponseDto cfrResponseDto = null;
        try {
            cfrResponseDto = mapper.readValue(response.getBody(), CFRResponseDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return cfrResponseDto;
    }

    private String convertBinary(MultipartFile files) throws Exception{
        String fileName = StringUtils.cleanPath(files.getOriginalFilename()) ;
        BufferedImage image = ImageIO.read(files.getInputStream());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, fileName.substring(fileName.lastIndexOf(".") + 1), baos);
        return new String(Base64.encodeBase64(baos.toByteArray()));
    }

}
