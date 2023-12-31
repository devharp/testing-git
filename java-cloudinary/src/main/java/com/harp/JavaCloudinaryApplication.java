package com.harp;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
@RestController
public class JavaCloudinaryApplication {

	private ImageUploader imageUploader;	

	@RequestMapping("/")
	public String home() {
		// return "Welcome to the home page!";
		return "redirect:/file-upload";
	}

	@GetMapping("/file-upload")
	public ResponseEntity<String> fileUploadForm() {

		try {
			ClassPathResource resource = new ClassPathResource("public/file-upload.html");
			String htmlContent = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
			return ResponseEntity.ok()
					.contentType(MediaType.TEXT_HTML)
					.body(htmlContent);
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
		}
	}

	@GetMapping("/hello")
	public String sayHello() {
		return "Hello, World!";
	}

	@PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("files") MultipartFile[] files) throws JsonProcessingException {
			List<String> filesPath = new ArrayList<String>();
			ObjectMapper objectMapper = new ObjectMapper();
			imageUploader = new ImageUploader("duiloptvq", "586615437941542", "5HSuzKJGHh_v4SIOQZy_c9HU8-0");
			
			Arrays.asList(files).forEach(file -> {
					String fileName = file.getOriginalFilename();
					Path filePath = Paths.get(System.getProperty("user.dir"), "uploads", fileName);
        	File destinationFile = filePath.toFile();

					try {
						if(destinationFile.exists()) destinationFile.delete();
						if (destinationFile.createNewFile()) {
								file.transferTo(destinationFile);
								System.out.println("File transferred: " + fileName);
								filesPath.add(imageUploader.uploadImage(new File(filePath.toAbsolutePath().toString())));
							}
					} catch (IOException e) {
						e.printStackTrace();
					}
			});
			System.out.println("FILES PATH: " + filesPath);
			return ResponseEntity.status(HttpStatus.OK).body(objectMapper.writeValueAsString(filesPath));
			
    }

	public static void main(String[] args) {
		SpringApplication.run(JavaCloudinaryApplication.class, args);
	}

}
