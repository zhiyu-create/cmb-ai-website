package com.example.cmbaiwebsite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.cmbaiwebsite.entity.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface TagMapper extends BaseMapper<Tag> {
    List<Tag> findTagsByArticleId(Long articleId);

    /**
     * 查询最热的标签前n条
     * @param limit
     * @return
     */
    List<Long> findHotsTagIds(int limit);

    List<Tag> findTagsByTagIds(List<Long> tagIds);
}
