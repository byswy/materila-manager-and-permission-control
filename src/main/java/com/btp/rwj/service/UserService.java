package com.btp.rwj.service;

import com.btp.rwj.entity.UserDetail;
import com.btp.rwj.entity.User;

import java.util.List;

public interface UserService {
    UserDetail login(User user);

    List<String> getPermissionsByUsername(String username);

    List<String> getRolesByUsername(String username);
}
