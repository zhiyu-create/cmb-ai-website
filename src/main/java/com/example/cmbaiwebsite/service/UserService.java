package com.example.cmbaiwebsite.service;

import com.example.cmbaiwebsite.entity.SysUser;
import com.example.cmbaiwebsite.vo.Result;
import com.example.cmbaiwebsite.vo.UserVo;

public interface UserService {

    SysUser findUserById(Long userId);

    SysUser findUser(String account, String pwd);

    /**
     * 根据token查询用户信息
     * @param token
     * @return
     */
    Result findUserByToken(String token);

    UserVo findUserVoById(Long id);
}
