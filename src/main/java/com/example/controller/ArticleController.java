package com.example.controller;


import com.example.entity.Compressimg;
import com.example.entity.JwtLogin;
import com.example.pojo.Article;
import com.example.service.ArticleService;
import com.example.service.ArticleServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/article")
public class ArticleController {

    //注入service
    @Autowired
    private ArticleServiceImpl articleService;


    @PostMapping("/add")
    @CrossOrigin//允许跨域请求
    public ResponseEntity<Map<String, Object>> add(
            @RequestParam("fileInput") MultipartFile file,
            @RequestParam("article_title") String article_title,
            @RequestParam("article_desc") String article_desc,
            @RequestParam("article_content") String article_content,
            @RequestParam("article_type") int article_type,
            @RequestHeader(value = "token") String token) throws IOException {

        Article article = new Article();
        JwtLogin jwtLogin = new JwtLogin();
        //获取发布者的id
        int Article_authorid = Integer.parseInt(jwtLogin.jwtPe(token).get("id").toString());
        //获取发布者的username
        String Article_auth= jwtLogin.jwtPe(token).get("username").toString();
        //这里以时间戳作为文件名称
        long date = new Date().getTime();
        String path = "/opt/project/articlecover/"+ date+".jpg";
        Compressimg compressimg = new Compressimg();
        compressimg.compressImage(file,300,150,0.8f,path);
        //设置参数
        article.setArticle_authorid(Article_authorid);
        article.setArticle_auth(Article_auth);
        article.setArticle_title(article_title);
        article.setArticle_desc(article_desc);
        article.setArticle_cover("/articlecover/"+ date+".jpg");
        article.setArticle_content(article_content);
        article.setArticle_type(article_type);
        article.setPublish_time(new Timestamp(new Date().getTime()));

        articleService.insertArticle(article);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("code", "200");
        response.put("msg", "发布文章成功");
        response.put("date", new Date().getTime());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get")
    @CrossOrigin//允许跨域请求
    public ResponseEntity<Map<String, Object>> login(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "article_type", required = false) String article_type
    ){
        //ArticleServiceImpl
        //获取一个文章实例
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("code", "200");
        if(id!=null){
            Article article  = articleService.queryArticleByid(id);
            response.put("msg", article);
        } else if (article_type!=null) {
            List<Article> articles  = articleService.queryArticleBytype(article_type);
            response.put("msg", articles);
            return ResponseEntity.ok(response);
        } else if (id==null && article_type==null) {
            List<Article> li  = articleService.queryArticle();
            response.put("msg", li);
        }


        response.put("date", new Date().getTime());

        return ResponseEntity.ok(response);

    }

}
