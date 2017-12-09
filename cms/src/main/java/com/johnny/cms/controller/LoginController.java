package com.johnny.cms.controller;

import com.johnny.cms.domain.User;
import com.johnny.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Author: Johnny
 * Date: 2017/11/30
 * Time: 下午19:32
 */
@Controller
public class LoginController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public boolean login(HttpServletRequest request, @RequestBody User user) throws Exception {
        //登陆之前从数据库取数据，判断是不是合法用户
        if (service.checkUsernameAndPassword(user)) {
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user);
            return Boolean.TRUE;
        } else {
            request.setAttribute("user", user);
            request.setAttribute("errorMsg", "用户名/密码错误或者用户不存在");
            return Boolean.FALSE;
        }
    }

    @RequestMapping("logout")
    @ResponseBody
    public void logout(HttpSession session) {
        session.invalidate();
    }

    /**
     * 注册所用的API
     * TODO: 使用 postman 带着 request body 发送这个 API，不会有密码强度校验，校验是前端做的
     *
     * @param user user
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public void add(@RequestBody User user) {
        if (user.getUserId() == null) {
            service.addUser(user);
        } else {
            service.updateUser(user);
        }
    }
}
