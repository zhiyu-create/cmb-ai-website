package com.example.cmbaiwebsite.service;

import com.example.cmbaiwebsite.vo.PageParams;
import com.example.cmbaiwebsite.vo.Result;

public interface ArticleService {
    /**
     * 分页查询文章列表
     * @param pageParams
     * @return
     */
    Result listArticle(PageParams pageParams);

    /**
     * 最热文章
     * @param limit
     * @return
     */
    Result hotArticle(int limit);

    /**
     * 最新文章
     * @param limit
     * @return
     */
    Result newArticles(int limit);

    /**
     * 文章归档
     * @return
     */
    Result listArchives();

    /**
     *根据文章id查询文章
     * @param articleId
     * @return
     */
    Result findArticleById(Long articleId);
}
