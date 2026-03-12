package com.idwdhao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data               // 生成 getter/setter、toString、equals、hashCode
@NoArgsConstructor  // 生成无参构造（反序列化必需）
@AllArgsConstructor // 生成全参构造
public class User {
    private String name;
    private Integer age;
}