package com.example.cmbaiwebsite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.cmbaiwebsite.dos.Archives;
import com.example.cmbaiwebsite.entity.Article;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ArticleMapper extends BaseMapper<Article> {

    List<Archives> listArchives();
}
