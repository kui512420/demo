package com.example.service;

import com.example.pojo.User;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public interface UserService {
    //查询所用用户信息
    public List<User> queryUser();
    //插入用户
    public Boolean insertUser(User user);
    //通过id来删除用户
    public int deleteById(Serializable id);
    //判断用户输入的账号和密码是否正确
    public Boolean verifyLogin(User user);
    //通过账号查id
    public int queryIdByusername(String username);
    public boolean updateLoginTime(int id, Timestamp timestamp);
}
