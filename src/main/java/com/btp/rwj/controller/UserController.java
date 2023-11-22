package com.btp.rwj.controller;

import com.btp.rwj.entity.UserDetail;
import com.btp.rwj.entity.User;
import com.btp.rwj.service.UserService;
import com.btp.rwj.utils.CommonUtil;
import com.btp.rwj.utils.JwtUtil;
import com.btp.rwj.vo.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping("")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ApiResult login(@RequestBody User user) {
        UserDetail userDetail = userService.login(user);
        if (userDetail == null)
            return ApiResult.fail("密码错误");
        else {
            String username = userDetail.getUsername();
            userDetail.setRoleList(userService.getRolesByUsername(username));
            List<String> permissionList = userService.getPermissionsByUsername(username);
            userDetail.setPermissionList(permissionList);
            userDetail.setToken(JwtUtil.getToken(userDetail.getUsername(), CommonUtil.List2String(permissionList)));
            userDetail.setTakenExpireTime(30 * 60);
            return ApiResult.success(userDetail);
        }
    }
}
