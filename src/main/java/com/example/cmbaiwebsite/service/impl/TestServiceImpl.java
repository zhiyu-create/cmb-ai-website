package com.example.cmbaiwebsite.service.impl;

import com.example.cmbaiwebsite.vo.Result;
import com.example.cmbaiwebsite.entity.Tag;
import com.example.cmbaiwebsite.mapper.TagMapper;
import com.example.cmbaiwebsite.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TestServiceImpl implements TestService {

    @Resource
    private TagMapper tagMapper;

    @Override
    public Result selectList(Long id) {
        Tag tag = tagMapper.selectByPrimaryKey(id);
        return Result.success(tag);
    }

}
