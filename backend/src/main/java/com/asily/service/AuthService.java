package com.asily.service;

import com.asily.entity.User;

import java.util.Map;

public interface AuthService {

    boolean register(User user);

    Map<String, Object> checkLogin(String name, String pwd);
}
