package com.btp.rwj.domain.PO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegister {
    private String name;
    private String username;
    private String password;
    private List<String> roles;
    private List<String> permissions;
}
