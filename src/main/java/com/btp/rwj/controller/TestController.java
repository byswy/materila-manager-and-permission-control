package com.btp.rwj.controller;

import com.btp.rwj.vo.ApiResult;
import com.btp.rwj.vo.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/list")
    public ApiResult list() {
        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person("zhangsan", 18, true));
        list.add(new Person("lisi", 19, false));
        return ApiResult.success(list);
    }

}