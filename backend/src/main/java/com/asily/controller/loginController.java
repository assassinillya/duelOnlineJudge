package com.asily.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.asily.entity.User;
import com.asily.service.AuthService;
import com.asily.utils.AESUtil;
import com.asily.utils.HTTPResponse;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 登录测试
 */
@RestController
@RequestMapping("/auth/")
public class loginController {

    @Resource
    AuthService authService;

    @Resource
    AESUtil aesUtil;

    @PostMapping("register")
    public HTTPResponse<User> register(@RequestBody User user) {

        String psw = aesUtil.generatePwd(user.getPassword());
        user.setPassword(psw);
        user.setRating(0);
        user.setSolvedCount(0);

        if (!authService.register(user)) {
            return HTTPResponse.failure(200, "用户名已被注册");
        }
        return HTTPResponse.success(user);
    }

    @PostMapping("login")
    public HTTPResponse<String> Login(@RequestBody User requestUser) {
        Map<String, Object> userBooleanMap = authService.checkLogin(requestUser.getUsername(), requestUser.getPassword());
        User user = (User) userBooleanMap.get("user");
        boolean flag = (boolean) userBooleanMap.get("res");
        if (flag) {
            StpUtil.login(user.getUserId());
            return HTTPResponse.success("登陆成功");
        }
        return HTTPResponse.failure(200, "用户名或密码不正确");
    }

    // 查询登录状态
    @RequestMapping("isLogin")
    public SaResult isLogin(@RequestBody User requestUser) {
        // 测试接口
        return SaResult.ok("是否登录：" + StpUtil.isLogin(requestUser.getUserId()));
    }

    // 查询 Token 信息
    @RequestMapping("tokenInfo")
    public SaResult tokenInfo(@RequestBody User requestUser) {
        StpUtil.
                StpUtil.getTokenInfo();
        // 测试接口
        return SaResult.data();
    }

    // 测试注销  ---- /logout
    @RequestMapping("logout")
    public HTTPResponse<String> logout(@RequestBody User requestUser) {
        StpUtil.logout(requestUser.getUserId());
        // TODO token失效加入redis黑名单
        return HTTPResponse.success("退出登录成功");
    }

}
