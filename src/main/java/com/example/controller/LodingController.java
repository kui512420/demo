package com.example.controller;

import com.example.entity.Compressimg;
import com.example.entity.JwtLogin;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class LodingController {

    @PostMapping("/upload-url")
    @CrossOrigin//允许跨域请求
    public ResponseEntity<Map<String,Object>> putimg(
            @RequestParam("fileInput") MultipartFile file,
            @RequestHeader(value = "token") String token) throws IOException {

        //这里以用户id作为文件名称
        JwtLogin jwtLogin = new JwtLogin();
        //获取id
        int id = Integer.parseInt(jwtLogin.jwtPe(token).get("id").toString());

        String path = "/opt/project/userheader/"+ id+".jpg";
        Compressimg compressimg = new Compressimg();
        compressimg.compressImage(file,300,300,0.8f,path);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("code",200);
        response.put("msg","上传头像成功！");
        response.put("data",new Date().getTime());
        return ResponseEntity.ok(response);
    }



}
