package com.example.cmbaiwebsite.controller;

import com.example.cmbaiwebsite.service.ArticleService;
import com.example.cmbaiwebsite.vo.ArticleVo;
import com.example.cmbaiwebsite.vo.PageParams;
import com.example.cmbaiwebsite.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * 首页 文章归档
     * @return
     */
    @PostMapping("listArchives")
    public Result listArchives(){
        return articleService.listArchives();
    }
    //Result是统一结果返回
    @PostMapping
    public Result articles(@RequestBody PageParams pageParams) {
        //ArticleVo 页面接收的数据
        Result result= articleService.listArticle(pageParams);

        return Result.success(result);
    }

    @PostMapping("view/{id}")
    public Result findArticleById(@PathVariable("id") Long articleId){
        return articleService.findArticleById(articleId);
    }

    @PostMapping
    //加上此注解，代表要对此接口记录日志
//    @LogAnnotation(module = "文章",operation = "获取文章列表")
    public Result listArticle(@RequestBody PageParams pageParams){

        return articleService.listArticle(pageParams);

    }
}
