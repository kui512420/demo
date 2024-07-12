package com.example.service;

import com.example.dao.UserMapper;
import com.example.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    // 获取所有用户信息
    @Override
    public List<User> queryUser() {
        log.info("进入业务逻辑 queryUser() 方法");
        return userMapper.selectList(null);
    }
    //插入用户
    @Override
    public Boolean insertUser(User user) {
        log.info("进入业务逻辑 queryUser() 方法");
        Boolean result = false;
        if(userMapper.insert(user)>=1){
            result = true;
        }

        return result;
    }
    //通过id删除用户信息
    @Override
    public int deleteById(Serializable id){
        return userMapper.deleteById(id);
    }

    //验证账号是否匹配
    @Override
    public Boolean verifyLogin(User u){
        boolean isLogin = false;
        User u1 = userMapper.selectByUsername(u.getUsername());

        if(u1 == null){
            return isLogin;
        }
        return u.getPassword().equals(u1.getPassword());
    }
    //通过账号查id
    @Override
    public int queryIdByusername(String username){
       User u = userMapper.selectByUsername(username);
       if(u==null){
           return -1;
       }else{
           return u.getId();
       }
    }

    //修改最后登录时间
    @Override
    public boolean updateLoginTime(int id, Timestamp timestamp){
        return userMapper.updateLoginTime(id,timestamp);
    }
}
