package com.idwdhao;

import com.idwdhao.mapper.UserMapper;
import com.idwdhao.pojo.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootWebQuickstartApplicationTests {

    @Autowired  //通过IOC容器获取对应的实体类对象，无需自己定义
    public UserMapper userMapper;

    @Test
    public void testListUser() {
        //获取到对象后放在自定义列表userList中，并用增强for遍历
        List<User> userList=userMapper.findAll();
        for(User user:userList){
            System.out.println(user);
        }
        System.out.println("----------------------");
        userList.stream().forEach(System.out::println);

    }

    @Test
    public void testDeleteUser() {

        System.out.println("----------------------");
        //删除对应ID的数据
        int delete = userMapper.Delete(8);
        System.out.println(delete);

        //删除后遍历看看结果
        List<User> userList=userMapper.findAll();
        for(User user:userList){
            System.out.println(user);
        }
    }

    @Test
    public void testInsertUser() {

        User user=new User();
        user.setName("赵高");
        user.setBalance(1000);
        System.out.println("----------------------");
        //添加数据
        int insert = userMapper.Insert(user);
        System.out.println(insert);

        //添加后遍历看看结果
        List<User> userList=userMapper.findAll();
        for(User userli:userList){
            System.out.println(userli);
        }
    }

    @Test
    public void testInsert_retUser() {

        User user=new User();
        user.setName("唐马");
        user.setBalance(4500);
        System.out.println("----------------------");
        //添加数据
        int insert = userMapper.Insert_ret(user);
        System.out.println(insert);

        //添加后遍历看看结果
        List<User> userList=userMapper.findAll();
        for(User userli:userList){
            System.out.println(userli);
        }
        System.out.println("-------------------------");
        System.out.println(user.getId());
    }

    @Test
    public void testUpdatetUser() {

        User user=new User();
        user.setId(9);
        user.setName("鲁大师");
        user.setBalance(5000);
        System.out.println("----------------------");
        //修改数据
        int update = userMapper.Update(user);
        System.out.println(update);

        //修改后遍历看看结果
        List<User> userList=userMapper.findAll();
        for(User userli:userList){
            System.out.println(userli);
        }
    }

    @Test
    public void testFindIDUser() {
        //根据id查数据
        //注意查出的数据封装到实体类对象的过程
        //实体类对象属性的名称与数据表的名称一致那么mybatis会自动封装
        //如果不一致，根据情况有三个解决方案
        //方法一：如果实体类属性的命名是驼峰命名（规范），数据库的数据表项的命名也是规范命名
        //那么可以在mybatis的配置文件中将驼峰命名的配置参数改为Ture
        //方法二：如果命名不规范，或者命名方式与法一一致，那么可以在sql语句中写别名来对齐双方的名字
        //方法三：如果命名不规范，或者命名方式与法一一致，那么可以用手动结果装载的方式，注解@Result来手动指定
        User user = userMapper.FindById(1);
        System.out.println(user);
    }

    @Test
    public void testFindfiUser() {

        //可以根据设置的内容进行查询，给出金额就按大于金额的方式查询
        //给出名字就按包含该名字的方式查询，都不给出就查所有
        User user1=new User();
        user1.setName(null);
        user1.setBalance(5000);

        List<User> list = userMapper.Findfi(user1);
        System.out.println(list);
    }

    @Test
    public void testUpdatet2User() {

        User user=new User();
        user.setId(9);
        user.setName("王二麻子");
        user.setBalance(null);
        System.out.println("----------------------");
        //修改数据
        int update = userMapper.Update2(user);
        System.out.println(update);

        //修改后遍历看看结果
        List<User> userList=userMapper.findAll();
        for(User userli:userList){
            System.out.println(userli);
        }
    }

}
