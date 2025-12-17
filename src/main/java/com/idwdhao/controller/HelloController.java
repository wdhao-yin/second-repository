package com.idwdhao.controller;

import com.idwdhao.pojo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//请求处理类
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public Result hello(){
        System.out.println("hello world!" );
        //return new Result(1,"success","hello world!");
        return Result.success("hello world!");
    }

    @RequestMapping("/getAdr")
    public Result getAdr(){
       return Result.success("重庆");
    }

    @RequestMapping("/list")
    public Result list(){
        ArrayList<Integer> list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        return Result.success(list);
    }


}
