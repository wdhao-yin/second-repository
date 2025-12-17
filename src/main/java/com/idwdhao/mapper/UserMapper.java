package com.idwdhao.mapper;

import com.idwdhao.pojo.User;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper //这个注解的作用是在运行时自动生成对应的接口实现类对象（代理对象），并将对象交给IOC容器管理
public interface UserMapper {
    //在接口中写对应的方法
    //使用注解（这个注解是Mybaits提供的），直接写sql语句，无需在意jdbc的底层实现
    //查询
    @Select("select * from jdbc_user")
    public List<User> findAll();


    //删除，注意删除会有返回值，返回值是影响的行数,一般不需要，#{}是注解sql的变量占位符，如果只有一个参数传入
    //那么占位符中的参数写什么都能对应
    //注意删除是delete
    @Delete("delete from jdbc_user where id = #{id}")
    public int Delete(int id);

    //插入数据，注意要插入多个数据时，可以用一个实现类来封装
    @Insert("insert into jdbc_user(name,balance) values (#{name},#{balance})")
    public int Insert(User user);

    //在插入注解上方再添加一个 @Options注解， useGeneratedKeys = true,keyProperty = "id"
    //一第个参数是要拿到生成的主键，第二个参数是生成的主键重新赋值给实体类对象
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into jdbc_user(name,balance) values (#{name},#{balance})")
    public int Insert_ret(User user);

    @Update("update jdbc_user set name=#{name},balance=#{balance} where id=#{id}")
    public int Update(User user);

    //根据id进行数据查询
    @Select("select * from jdbc_user where id=#{id}")
    public User FindById(int id);

    //根据特定条件进行数据查询
    //模糊查询用like，可以使用mysql的内置函数concat来连接字符串。
    //springboot 1.x版本，要用@param注解，来指定每一个数据库参数对应的方法参数
    //2.x版本只需要保证名字一致，无需写注解
//    @Select("select * from jdbc_user where  name like concat('%',#{name},'%') and balance>1000")
//    public User Findfi(String name);

//    @Select("select * from jdbc_user where  name like concat('%',#{name},'%') and balance>1000")
    //动态sql语句使用<where>标签和<if>标签，可以根据传入的参数来生成对应的sql语句
    //if标签会根据条件判断(使用test属性)是否加入对应的sql语句，而where标签则会根据条件满足的数量进行对应的拼接，会识别and
    //如果有多余的and会删掉，而且如果条件均不满足，则where会自动将自己去掉，转换成正常的sql查询语句
    public List<User> Findfi(User user);

    //动态sql语句 更新数据
//    @Update("update jdbc_user set name=#{name},balance=#{balance} where id=#{id}")
    public int Update2(User user);

}
