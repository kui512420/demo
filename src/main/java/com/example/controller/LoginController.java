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
public class LoginController {

    //注入service
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/login")
    @CrossOrigin//允许跨域请求
    public ResponseEntity<Map<String, Object>> login(
            @RequestParam("username") String username,
            @RequestParam("password") String password) {
        int id = userService.queryIdByusername(username);

        Timestamp timestamp = new Timestamp(new Date().getTime());
        User u = new User(id,username, password);
        u.setLast_login(timestamp);

        //验证用户是否正常登录成功
        boolean isLogin = userService.verifyLogin(u);

        //更新用户最新登录时间
        //传入id与时间
        userService.updateLoginTime(u.getId(),timestamp);

        if(isLogin){
            Map<String, Object> response = new LinkedHashMap<>();
            response.put("code", "200");
            response.put("msg", "登录成功");
            JwtLogin jl = new JwtLogin();

            response.put("token", jl.jwtBd(u));
            response.put("date", new Date().getTime());
            return ResponseEntity.ok(response);
        }else{
            Map<String, Object> response = new LinkedHashMap<>();
            response.put("code", "201");
            response.put("msg", "登录失败");
            response.put("date", new Date().getTime());
            return ResponseEntity.ok(response);
        }
    }

    @ExceptionHandler({MissingServletRequestParameterException.class, DuplicateKeyException.class,NullPointerException.class})
    @ResponseBody
    public Map<Object, Object> handleExceptions(Exception ex) {
        Map<Object, Object> result = new LinkedHashMap<>();
        if (ex instanceof MissingServletRequestParameterException) {
            result.put("code", 201);
            result.put("msg", "登录失败,缺少必要参数");
        }if(ex instanceof NullPointerException){
            result.put("code", 202);
            result.put("msg", "登录失败,账号不存在");
        }else {
            result.put("code", "500");
            result.put("msg", "发生未知异常");
        }
        result.put("date", new Date().getTime());
        return result;
    }
}
