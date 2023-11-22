package com.btp.rwj.mapper;

import com.btp.rwj.entity.UserDetail;
import com.btp.rwj.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    UserDetail login(User user);

    List<String> getPermissionsByUsername(String username);

    List<String> getRolesByUsername(String username);
}
