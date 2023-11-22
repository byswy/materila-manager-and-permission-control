package com.btp.rwj.service.impl;

import com.btp.rwj.mapper.UserMapper;
import com.btp.rwj.entity.UserDetail;
import com.btp.rwj.entity.User;
import com.btp.rwj.service.UserService;
import com.btp.rwj.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetail login(User user) {
        String pwd = user.getPwd();
        String pwd_salt = Md5Util.md5pwd(pwd);
//        System.out.println(pwd_salt);
//        System.out.println(Md5Util.md5pwd("123456"));
        user.setPwd(pwd_salt);
        return userMapper.login(user);
    }

    public List<String> getPermissionsByUsername(String username) {
        return userMapper.getPermissionsByUsername(username);
    }

    public List<String> getRolesByUsername(String username) {
        return userMapper.getRolesByUsername(username);
    }
}
