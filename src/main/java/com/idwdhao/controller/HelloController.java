package com.idwdhao.controller;

import com.idwdhao.pojo.Result;
import com.idwdhao.pojo.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//这个注解的作用是在这个类中能够直接使用日志log，无需自己new一个对象
@Slf4j
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

    //可以对请求的类型作出限制,下面这个就是只能使用get类型的请求，还有第二种快捷写法
//    @RequestMapping(value = "/getStudent",method = RequestMethod.GET)
    //其他类型的快捷注解都有对应的Mapping
    @GetMapping("/getStudent")
    public Result getStudent(){
        Student student = new Student("小明","男","1234560");
        Result result = new Result();
        result.setData(student);
        result.setCode(200);
        result.setMsg("查询成功！");
        log.info("日志输出成功！");
        return result;
    }

}
