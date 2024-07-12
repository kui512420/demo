package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Timestamp;

public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT * FROM a WHERE username = #{username}")
    User selectByUsername(@Param("username") String username);

    @Update("UPDATE a SET last_login = #{timestamp} where id = #{id}")
    boolean updateLoginTime(@Param("id") int id, @Param("timestamp")Timestamp timestamp);
}