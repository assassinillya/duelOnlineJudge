package com.asily.service.impl;

import com.asily.components.AESUtil;
import com.asily.entity.User;
import com.asily.mapper.UserMapper;
import com.asily.service.AuthService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("authService")
@Transactional
public class authServiceImpl implements AuthService {

    @Resource
    UserMapper userMapper;

    @Resource
    AESUtil aesUtil;

    @Override
    public boolean register(User user) {
        if (userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, user.getUsername())) == null) {
            userMapper.insert(user);
            return true;
        }
        return false;
    }

    @Override
    public Map<String, Object> checkLogin(String name, String pwd) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, name));
        Map<String, Object> result = new HashMap<>();
        boolean flag = true;
        if (user == null) {
            // 这个用户不存在
            flag = false;
            result.put("res", flag);
            result.put("user", user);
            return result;
        }

        if (!aesUtil.checkPwd(pwd, user.getPassword())) {
            // 密码错误
            flag = false;
        }
        result.put("res", flag);
        result.put("user", user);
        return result;

    }
}
