package com.btp.rwj.domain.DO;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("rwj_user")
public class User {
    private Long id;
    private String name;
    private String username;
    private String password;
    private Boolean deleted;//
    //    @TableField(fill = FieldFill.INSERT)
    private Date createTime;//
    private String createBy;
    //    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;//
    private String updateBy;
    private Date lastLogin;
}
