package com.example.service;

import com.example.dao.ArticleMapper;
import com.example.pojo.Article;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    private ArticleMapper articleMapper;

    //插入文章
    @Override
    public boolean insertArticle(Article article) {
        int count = articleMapper.insert(article);
        if(count>=1){
            return true;
        }else{
            return false;
        }
    }
    //查询所有文章
    @Override
    public List<Article> queryArticle(){
        log.info("进入业务逻辑 queryArticle() 方法");
        List<Article> l = articleMapper.selectList(null);
        return l;
    }

    @Override
    public Article queryArticleByid(String id) {
        Article article = articleMapper.selectById(id);
        return article;
    }
}
