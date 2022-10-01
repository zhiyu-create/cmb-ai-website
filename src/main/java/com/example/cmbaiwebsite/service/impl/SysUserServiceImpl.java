package com.example.cmbaiwebsite.service.impl;

import com.example.cmbaiwebsite.entity.SysUser;
import com.example.cmbaiwebsite.mapper.SysUserMapper;
import com.example.cmbaiwebsite.service.UserService;
import com.example.cmbaiwebsite.vo.Result;
import com.example.cmbaiwebsite.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser findUserById(Long id) {
        //根据id查询
        //为防止sysUser为空增加一个判断
        SysUser sysUser = sysUserMapper.selectById(id);
        if (sysUser == null){
            sysUser = new SysUser();
            sysUser.setNickname("代码之路");
        }
        return sysUser;
    }

    @Override
    public SysUser findUser(String account, String pwd) {
        return null;
    }

    @Override
    public Result findUserByToken(String token) {
        return null;
    }

    @Override
    public UserVo findUserVoById(Long id) {
        SysUser sysUser = sysUserMapper.selectById(id);
        if (sysUser == null){
            sysUser = new SysUser();
            sysUser.setId(1L);
            sysUser.setAvatar("/static/img/logo.b3a48c0.png");
            sysUser.setNickname("xx之路");
        }
        UserVo userVo = new UserVo();
        userVo.setAvatar(sysUser.getAvatar());
        userVo.setNickname(sysUser.getNickname());
        userVo.setId(sysUser.getId());
        return userVo;
    }
}
