package com.example.pojo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data//自动生成构造器和get/set方法
@TableName(value = "a")//标明实体类对应的表
//实现Serializable接口，是因为mybatis plus 要序列化
//比如在int deleteById(Serializable id);方法中，要求被序列化的id来删除数据
public class User implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("username")
    private String username;
    @TableField("password")
    private String password;
    @TableField("register_time")
    private Timestamp register_time;
    @TableField("last_login")
    private Timestamp last_login;
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public User(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(Integer id, String username, String password, Timestamp register_time, Timestamp last_login) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.register_time = register_time;
        this.last_login = last_login;
    }
}
