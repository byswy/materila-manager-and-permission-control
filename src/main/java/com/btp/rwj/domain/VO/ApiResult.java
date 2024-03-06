package com.btp.rwj.domain.VO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResult {
    private Integer errcode;
    private String errmsg;
    private Object data;

    public static ApiResult fail() {
        return new ApiResult(1, null, null);
    }

    public static ApiResult fail(String errmsg) {
        return new ApiResult(1, errmsg, null);
    }

    public static ApiResult fail(Integer errcode, String errmsg) {
        return new ApiResult(errcode, errmsg, null);
    }

    public static ApiResult success(Object data) {
        return new ApiResult(0, null, data);
    }

    public static ApiResult success() {
        return new ApiResult(0, null, null);
    }
}
