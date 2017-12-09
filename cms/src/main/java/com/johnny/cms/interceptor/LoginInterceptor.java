package com.johnny.cms.interceptor;

import com.johnny.cms.domain.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Author: Johnny
 * Date: 2017/12/1
 * Time: 下午7:24
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception ex) throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*通过 session 的方式*/
        String localUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");
        String url = request.getRequestURI();
//        if (url.contains("static")) return true;
        //TODO: validate/login/register这种逻辑要被过滤掉啊，不拦截
        if (StringUtils.isNoneBlank(url) && !url.toLowerCase().contains("login") && !url.toLowerCase().contains("register")) {
            if (null == user || "".equals(user)) {
                response.sendRedirect(localUrl + "/login.jsp");//
                return false;
            } else {
                return true;
            }
        }
        return true;
    }
}
