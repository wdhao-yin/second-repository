package com.idwdhao.pojo;

public class User {
    private Integer id;//在定义数据库对应的实体类时，注意使用对应的包装类
    private String name;
    private Integer balance;


    public User() {
    }

    public User(Integer id, String name, Integer balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    /**
     * 获取
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return balance
     */
    public Integer getBalance() {
        return balance;
    }

    /**
     * 设置
     * @param balance
     */
    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String toString() {
        return "User{id = " + id + ", name = " + name + ", balance = " + balance + "}";
    }
}
