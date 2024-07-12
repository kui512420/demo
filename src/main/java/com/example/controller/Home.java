package com.example.controller;

import com.example.entity.JwtLogin;
import com.example.pojo.User;
import com.example.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class Home {

    //注入service
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/getinfo")
    @CrossOrigin//允许跨域请求
    public ResponseEntity<Map<String, Object>> getinfo(
            @RequestParam("token") String token) {

        JwtLogin jwtLogin = new JwtLogin();
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("code", "200");
        response.put("msg", jwtLogin.jwtPe(token));
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseBody
    public Map<Object, Object> handleExceptions(Exception ex) {
        Map<Object, Object> result = new LinkedHashMap<>();
        if (ex instanceof MissingServletRequestParameterException) {
            result.put("code", 201);
            result.put("msg", "请求失败,缺少必要参数");
            result.put("date", new Date().getTime());
        }

        return result;
    }
}
