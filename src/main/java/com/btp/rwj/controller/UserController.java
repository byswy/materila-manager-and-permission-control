package com.btp.rwj.controller;

import com.btp.rwj.domain.DO.User;
import com.btp.rwj.domain.PO.UserRegister;
import com.btp.rwj.domain.VO.ApiResult;
import com.btp.rwj.domain.VO.UserVO;
import com.btp.rwj.service.IUserService;
import com.btp.rwj.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("register")
    public ApiResult register(@RequestBody UserRegister userRegister) {
        String password = userRegister.getPassword();
        userRegister.setPassword(Md5Util.md5pwd(password));
        if (userService.register(userRegister)) {
            return ApiResult.success();
        } else {
            return ApiResult.fail("注册失败");
        }
    }

    @PostMapping("login")
    public ApiResult login(@RequestBody User user) {
        UserVO userVO = userService.login(user);
        if (userVO == null) {
            return ApiResult.fail("用户不存在或密码错误");
        }
        return ApiResult.success(userVO);
    }
}
