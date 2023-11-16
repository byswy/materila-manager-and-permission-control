package com.btp.rwj.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Person {

    private String name="张三三";
    private Integer age;
    private Boolean student=true;

}
