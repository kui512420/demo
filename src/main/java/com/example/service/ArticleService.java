package com.example.service;

import com.example.pojo.Article;
import com.example.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface ArticleService {
    //插入文章
    public boolean insertArticle(Article article);
    //查询所有文章
    public List<Article> queryArticle();
    //通过id查文章
    public Article queryArticleByid(String id);
    //通过类型查询文章
    public List<Article> queryArticleBytype(String type);

}
