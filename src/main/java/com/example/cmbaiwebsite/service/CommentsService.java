package com.example.cmbaiwebsite.service;


import com.example.cmbaiwebsite.vo.CommentParam;
import com.example.cmbaiwebsite.vo.Result;

public interface CommentsService {

    /**
     * 根据文章id查询所有的评论列表
     * @param id
     * @return
     */
    Result commentsByArticleId(Long id);

    Result comment(CommentParam commentParam);

}

