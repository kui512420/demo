package com.example.demo;

import com.example.entity.JwtLogin;
import com.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class Demo1ApplicationTests {
    // 注入业务逻辑接口实例
    @Autowired
    private UserService userService;
    @Test
    void a(){

    }


}
