package com.example.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/*
* 使外部可以正常访问图片
* */
@RestController
public class ImageController {

    private static final String UPLOAD_DIR = "/opt/project/userheader/";
    private static final String UPLOAD_DIR2 = "/opt/project/articlecover/";

    @GetMapping("/userheader/{filename:.+}")
    public ResponseEntity<byte[]> getImage(@PathVariable String filename) throws IOException {
        Path filePath = Paths.get(UPLOAD_DIR).resolve(filename).normalize();

        // 读取文件内容
        byte[] imageBytes = Files.readAllBytes(filePath);

        // 设置Content-Type为image/jpeg
        MediaType mediaType = MediaType.IMAGE_JPEG;

        // 构建响应
        return ResponseEntity.ok()
                .contentType(mediaType)
                .body(imageBytes);
    }

    @GetMapping("/articlecover/{filename:.+}")
    public ResponseEntity<byte[]> getImage2(@PathVariable String filename) throws IOException {
        Path filePath = Paths.get(UPLOAD_DIR2).resolve(filename).normalize();

        // 读取文件内容
        byte[] imageBytes = Files.readAllBytes(filePath);

        // 设置Content-Type为image/jpeg
        MediaType mediaType = MediaType.IMAGE_JPEG;

        // 构建响应
        return ResponseEntity.ok()
                .contentType(mediaType)
                .body(imageBytes);
    }
}

