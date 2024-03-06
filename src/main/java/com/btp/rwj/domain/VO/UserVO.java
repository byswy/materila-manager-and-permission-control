package com.btp.rwj.domain.VO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    private Long id;
    private String name;
    private String username;
    private List<String> roles;
    private List<String> permissions;
    private String token;
}
