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
    public ApiResult list(){
        ArrayList<Object> list = new ArrayList<>();
        for(int i=0;i<100;i++){
            list.add(new Person().setAge(i).setName("李四"+i).setStudent(true));
        }
        return ApiResult.success(list);
    }

}