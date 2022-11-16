package com.movielog.domain;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.movielog.service.MemberService;

public class RememberInterceptor extends HandlerInterceptorAdapter {


    @Inject
    private MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession httpSession = request.getSession();
        Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
        if (loginCookie != null) {
            MemberVO memberVO = memberService.checkLoginBefore(loginCookie.getValue());
            if (memberVO != null)
                httpSession.setAttribute("login", memberVO);
        }

        return true;
    }
}