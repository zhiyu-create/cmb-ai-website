package com.example.cmbaiwebsite.service;

import com.example.cmbaiwebsite.vo.Result;
import com.example.cmbaiwebsite.vo.TagVo;

import java.util.List;

public interface TagsService {
    List<TagVo> findTagsByArticleId(Long id);


    Result hots(int limit);
}
