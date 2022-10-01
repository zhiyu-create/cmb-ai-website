package com.example.cmbaiwebsite.service;

import com.example.cmbaiwebsite.entity.SysUser;
import com.example.cmbaiwebsite.vo.LoginParam;
import com.example.cmbaiwebsite.vo.Result;

public interface LoginService {
    /**
     * 登录
     * @param loginParam
     * @return
     */
    Result login(LoginParam loginParam);

    SysUser checkToken(String token);
}
