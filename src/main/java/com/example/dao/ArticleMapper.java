package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.Article;
import com.example.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ArticleMapper extends BaseMapper<Article> {

    @Select("SELECT * FROM b WHERE article_type = #{article_type}")
    List<Article> selectByArticleType(@Param("article_type") String article_type);

}
