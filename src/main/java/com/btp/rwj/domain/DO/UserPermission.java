package com.btp.rwj.domain.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("rwj_user_permission")
public class UserPermission {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long uid;
    private String permission;
}
