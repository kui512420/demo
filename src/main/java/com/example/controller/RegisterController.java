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

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class RegisterController {

    //注入service
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/register")
    @CrossOrigin//允许跨域请求
    public ResponseEntity<Map<String, Object>> register(
            @RequestParam("username") String username,
            @RequestParam("password") String password) {
        Timestamp timestamp = new Timestamp(new Date().getTime());

        User u = new User(username, password);
        u.setRegister_time(timestamp);
        //此方法可能会抛出异常(注册不成功)
        userService.insertUser(u);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("code", "200");
        response.put("msg", "注册成功");
        JwtLogin jl = new JwtLogin();
        String token = jl.jwtBd(u);
        response.put("token", token);
        response.put("date", new Date().getTime());
        return ResponseEntity.ok(response);
    }
    @PostMapping("/isRegister")
    @CrossOrigin//允许跨域请求
    public ResponseEntity<Map<String, Object>> isRegister(
            @RequestParam("username") String username) {
        Timestamp timestamp = new Timestamp(new Date().getTime());


        //此方法可能会抛出异常(注册不成功)
        int id = userService.queryIdByusername(username);

        Map<String, Object> response = new LinkedHashMap<>();
        if(id!=-1){
            response.put("code", "202");
            response.put("msg", "账号已存在");
        }else{
            response.put("code", "200");
            response.put("msg", "未注册");
        }
        response.put("date", new Date().getTime());
        return ResponseEntity.ok(response);
    }
    @ExceptionHandler({MissingServletRequestParameterException.class, DuplicateKeyException.class})
    @ResponseBody
    public Map<Object, Object> handleExceptions(Exception ex) {
        Map<Object, Object> result = new LinkedHashMap<>();
        if (ex instanceof MissingServletRequestParameterException) {
            result.put("code", 201);
            result.put("msg", "注册失败,缺少必要参数");
        } else if (ex instanceof DuplicateKeyException) {
            result.put("code", 202);
            result.put("msg", "注册失败,账号已存在");
        } else {
            result.put("code", "500");
            result.put("msg", "发生未知异常");
        }
        result.put("date", new Date().getTime());
        return result;
    }
}
