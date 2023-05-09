package com.test.postgresql_test.Service.ServiceImpl;

import com.test.postgresql_test.Service.NaverCFRService;
import com.test.postgresql_test.domain.dto.CFRResponseDto;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
    public String getCFR(MultipartFile multipartFile) throws Exception {
        String boundary = "--" + convertBinary(multipartFile); //위 보고 boundary 추가
        System.out.println(boundary);
        RestTemplate rt = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type",  "multipart/form-data; boundary=" + boundary);
        httpHeaders.add("X-Naver-Client-Id", X_Naver_Client_Id);
        httpHeaders.add("X-Naver-Client-Secret", X_Naver_Client_Secret);

        HttpEntity<MultiValueMap<String, String>> CFRRequest =
                new HttpEntity<>(null, httpHeaders);

        ResponseEntity<String> response = rt.exchange(
                "https://openapi.naver.com/v1/vision/celebrity",
                HttpMethod.POST,
                CFRRequest,
                String.class
        );

        return response.getBody();
    }

    private String convertBinary(MultipartFile files) throws Exception{
        String fileName = StringUtils.cleanPath(files.getOriginalFilename()) ;
        BufferedImage image = ImageIO.read(files.getInputStream());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, fileName.substring(fileName.lastIndexOf(".") + 1), baos);
        return new String(Base64.encodeBase64(baos.toByteArray()));
    }

    public void getTest() {

        StringBuffer reqStr = new StringBuffer();
        String clientId = "YOUR_CLIENT_ID";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "YOUR_CLIENT_SECRET";//애플리케이션 클라이언트 시크릿값";

        try {
            String paramName = "image"; // 파라미터명은 image로 지정
            String imgFile = "파일경로";
            File uploadFile = new File(imgFile);
            String apiURL = "https://openapi.naver.com/v1/vision/celebrity"; // 유명인 얼굴 인식
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);
            // multipart request
            String boundary = "---" + System.currentTimeMillis() + "---";
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            OutputStream outputStream = con.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);
            String LINE_FEED = "\r\n";
            // file 추가
            String fileName = uploadFile.getName();
            writer.append("--" + boundary).append(LINE_FEED);
            writer.append("Content-Disposition: form-data; name=\"" + paramName + "\"; filename=\"" + fileName + "\"").append(LINE_FEED);
            writer.append("Content-Type: "  + URLConnection.guessContentTypeFromName(fileName)).append(LINE_FEED);
            writer.append(LINE_FEED);
            writer.flush();
            FileInputStream inputStream = new FileInputStream(uploadFile);
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
            inputStream.close();
            writer.append(LINE_FEED).flush();
            writer.append("--" + boundary + "--").append(LINE_FEED);
            writer.close();
            BufferedReader br = null;
            int responseCode = con.getResponseCode();
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                System.out.println("error!!!!!!! responseCode= " + responseCode);
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            }
            String inputLine;
            if(br != null) {
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                System.out.println(response.toString());
            } else {
                System.out.println("error !!!");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
