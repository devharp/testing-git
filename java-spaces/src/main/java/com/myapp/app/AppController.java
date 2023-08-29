package com.myapp.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import org.springframework.util.StringUtils;

@RestController
public class AppController {

  private final String uploadDir = "uploads";
  private AppService appService = new AppService();
  private final String NODEJS_SERVER_URL = "http://localhost:8081"; // Change this to your Node.js server's URL
  private final RestTemplate restTemplate = new RestTemplate();

  @GetMapping("/")
  public RedirectView hello() {
    RedirectView redirectView = new RedirectView();
    redirectView.setUrl("/index.html");

    return redirectView;
  }

  @PostMapping("/upload")
  public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile file) throws InvalidKeyException, NoSuchAlgorithmException {
    try {
      String fileName = StringUtils.cleanPath(file.getOriginalFilename());
      Path filePath = Paths.get(uploadDir, fileName);

      System.out.println("File path: " + filePath.toString());

      if (!Files.exists(filePath.getParent())) {
        Files.createDirectories(filePath.getParent());
      }

      // Save the uploaded file to the specified directory
      file.transferTo(filePath);

      byte[] imageBytes = file.getBytes();

      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.setContentType(MediaType.APPLICATION_JSON);
      Map<String, Object> payloadObject = new HashMap<>();
      payloadObject.put("imageBytes", imageBytes);
      payloadObject.put("contentType", file.getContentType());
      payloadObject.put("contentLength", imageBytes.length);
      payloadObject.put("imageName", this.appService.generateRandomString(15) + fileName);
      
      HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(payloadObject, httpHeaders);

      // ResponseEntity<String, Object> response = restTemplate.postForEntity(NODEJS_SERVER_URL + "/forward-image", imageBytes, String.class);

      ResponseEntity<String> response = restTemplate.exchange(
            NODEJS_SERVER_URL + "/forward-image",
            HttpMethod.POST,
            requestEntity,
            String.class
        );

      System.out.println(response.getStatusCode());
      


      return ResponseEntity.status(HttpStatus.OK).body("Image uploaded successfully!");
    } catch (IOException e) {
      // System.out.println(e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image.");
    }
  }
}

/*
 HttpHeaders httpHeaders = new HttpHeaders();
      String signature = this.appService.generateSignature(file.getContentType(), this.appService.getCurrentData(), fileName, "+SL/5zTF+ZbQ5iWKp8ZC4j53+mSBo2Kh8U74oJywxlI");
      System.out.println("content type: " + file.getContentType());
      System.out.println("file name: " + fileName);
      System.out.println("signature: " + signature);

      httpHeaders.set("Host", "photoupload.nyc3.digitaloceanspaces.com");
      httpHeaders.set("Date", this.appService.getCurrentData());
      httpHeaders.set("Content-Type", file.getContentType());
      httpHeaders.set("Content-Length", String.valueOf(imageBytes.length));
      httpHeaders.set("x-amz-storage-class", "STANDARD");
      httpHeaders.set("x-amz-acl", "private");
      httpHeaders.set("Authorization", "AWS DO00HM9JKCRNT3RT2K6G:" + signature);

      RestTemplate restTemplate = new RestTemplate();
      HttpEntity<byte[]> requesHttpEntity = new HttpEntity<byte[]>(imageBytes, httpHeaders);
      ResponseEntity<String> response = restTemplate.exchange(
            "https://photoupload.nyc3.digitaloceanspaces.com/"+fileName,
            HttpMethod.PUT,
            requesHttpEntity,
            String.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
          System.out.println("Image uploaded to external server successfully.");
      } else {
          System.out.println("Failed to upload image to external server.: " + response.getBody());
      } 
 
 */

/*
const SPACE = 'photoupload';
const REGION = 'nyc3';
const STORAGETYPE = 'STANDARD';
const KEY = 'DO00WB6W2ANRJTGC4THJ';
const SECRET = 'V9lxppIfpQ0xRLDw3DB25AEbHc3rEV/yqu8wZTQYBL4';

 hostname: `${SPACE}.${REGION}.digitaloceanspaces.com`,
    path: `${spacePath}image.jpg`,
    method: 'PUT',
    headers: {
        Host: `${SPACE}.${REGION}.digitaloceanspaces.com`,
        Date: date,
        'Content-Type': contentType,
        'Content-Length': fileContent.length,
        'x-amz-storage-class': 'STANDARD',
        'x-amz-acl': 'private',
        'Authorization': `AWS ${KEY}:${signature}`
    }
*/