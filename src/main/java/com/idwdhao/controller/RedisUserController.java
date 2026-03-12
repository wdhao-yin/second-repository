package com.idwdhao.controller;

import com.idwdhao.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redis/user")
public class RedisUserController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 保存用户到 Redis
     * @param id 用户ID（路径变量）
     * @param user 用户对象（JSON 格式）
     * @return 操作结果
     */
    @PostMapping("/{id}")
    public String saveUser(@PathVariable Integer id, @RequestBody User user) {
        String key = "user:" + id;
        redisTemplate.opsForValue().set(key, user);
        return "用户已保存，key: " + key;
    }

    /**
     * 根据 ID 获取用户
     * @param id 用户ID
     * @return 用户对象
     */
    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id) {
        String key = "user:" + id;
        return (User) redisTemplate.opsForValue().get(key);
    }

    /**
     * 根据 ID 删除用户
     * @param id 用户ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Integer id) {
        String key = "user:" + id;
        Boolean deleted = redisTemplate.delete(key);
        return deleted ? "删除成功" : "用户不存在";
    }

    /**
     * 更新用户余额（示例：增加余额）
     * @param id 用户ID
     * @param amount 增加的金额
     * @return 操作结果
     */
    @PutMapping("/{id}/balance/{amount}")
    public String updateBalance(@PathVariable Integer id, @PathVariable Integer amount) {
        String key = "user:" + id;
        User user = (User) redisTemplate.opsForValue().get(key);
        if (user == null) {
            return "用户不存在";
        }
        user.setBalance(user.getBalance() + amount);
        redisTemplate.opsForValue().set(key, user);
        return "余额更新成功，当前余额：" + user.getBalance();
    }
}