package com.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@TableName(value = "b")
public class Article implements Serializable {
    @TableId(value = "article_id", type = IdType.AUTO)
    private Integer article_id;
    @TableField("article_auth")
    private String article_auth;
    @TableField("article_title")
    private String article_title;
    @TableField("article_desc")
    private String article_desc;
    @TableField("article_cover")
    private String article_cover;
    @TableField("article_content")
    private String article_content;
    @TableField("article_authorid")
    private int article_authorid;
    @TableField("article_type")
    private int article_type;
    @TableField("publish_time")
    private Timestamp publish_time;
    @TableField("last_time")
    private Timestamp last_time;

}
