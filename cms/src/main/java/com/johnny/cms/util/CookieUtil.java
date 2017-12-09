package com.johnny.cms.util;

import com.johnny.cms.domain.User;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Objects;

/**
 * Author: Johnny
 * Date: 2017/12/1
 * Time: 下午7:26
 */
public class CookieUtil {
    /**
     * 默认Cookie过期时间（单位：秒）
     */
    private static final int MAX_AGE = 60 * 30;

    /**
     * 用户登陆信息Cookie名字
     */
    private static final String USER_INFO = "currentUser";

    /**
     * 向Cookie中写入用户信息
     *
     * @param response response
     * @param user     user
     */
    public static void setLoginUser(HttpServletResponse response, User user) {
        if (null == response || null == user) {
            return;
        }
        long userId = user.getUserId();
        String username = user.getUserName();
        try {
            username = URLEncoder.encode(user.getUserName(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        StringBuilder cookieValue = new StringBuilder();
//        cookieValue.append(userId).append("|").append(username).append("|").append(user.getLoginStatus());
        cookieValue.append(userId).append("|").append(username);
        addCookie(response, USER_INFO, cookieValue.toString());
    }

    /*将构造好的信息放入coolie中*/
    private static void addCookie(HttpServletResponse response, String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(MAX_AGE);
        response.addCookie(cookie);
    }

    /*从cookie取出用户登陆信息并且构造User对象返回*/
    public static User getLoginUser(HttpServletRequest request) {
        if (null == request) {
            return null;
        }

        //从cookie里取出用户信息
        String value = getCookieValue(USER_INFO, request);
        if (StringUtils.isEmpty(value)) {
            return null;
        }

        String[] array = value.split("\\|");

        User user = new User();
        user.setUserId(Integer.parseInt(array[0]));
        try {
            user.setUserName(URLDecoder.decode(array[1], "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            user.setUserName(array[1]);
        }
        return user;
    }

    /*从cookie中取出用户的登陆信息*/
    private static String getCookieValue(String name, HttpServletRequest request) {
        if (null == request || StringUtils.isEmpty(name)) {
            return null;
        }

        Cookie[] cookies = request.getCookies();
        if (null == cookies || 0 == cookies.length) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if (Objects.equals(cookie.getName(), name)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    /**
     * 删除Cookie
     *
     * @param response HttpServletResponse
     * @param name     Cookie名
     * @param path     Cookie Path
     */
    public static void removeCookie(HttpServletResponse response, String name, String path) {
        if (null == response || StringUtils.isEmpty(name) || StringUtils.isEmpty(path)) {
            return;
        }
        Cookie cookie = new Cookie(name, "");
        cookie.setPath(path);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    //删除所有cookie
    public static void removeAllCookies(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            cookie.setMaxAge(0);
            cookie.setPath("/");
            resp.addCookie(cookie);
        }
    }
}
