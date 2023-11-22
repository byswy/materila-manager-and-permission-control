package com.btp.rwj.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetail {
    private String token;
    private long takenExpireTime;
    private Integer id;
    private String name;
    private String username;
    private List<String> roleList;
    private List<String> permissionList;


}
