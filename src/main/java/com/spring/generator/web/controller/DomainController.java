package com.spring.generator.web.controller;

import com.spring.generator.domain.DomainGenerator;
import com.spring.generator.service.GeneratorService;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class DomainController {

    @Autowired
    private GeneratorService jsonService;


    @PostMapping("/generateEntity")
    public ResponseEntity<byte[]> generateJavaClass(@RequestBody DomainGenerator jsonModel) {
        try {
            // return jsonService.createJavaFile(jsonModel);
            File tempFile = jsonService.createJavaFile(jsonModel);

             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            try (ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream)) {
                // Add each file in the folder to the zip
                for (File file : tempFile.listFiles()) {
                    if (file.isFile()) {
                        ZipEntry zipEntry = new ZipEntry(file.getName());
                        zipOutputStream.putNextEntry(zipEntry);

                        byte[] fileContent = Files.readAllBytes(file.toPath());
                        zipOutputStream.write(fileContent);

                        zipOutputStream.closeEntry();
                    }
                }
            }

            // Set response headers for the zip file
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "zipped_folder.zip");

            // Clean up: delete the files in the folder
            for (File file : tempFile.listFiles()) {
                file.delete();
            }

            // Delete the folder
            tempFile.delete();

            // Return the zip file as a byte array in the response
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(byteArrayOutputStream.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    
}
