package com.movielog.domain;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final String LOGIN = "login";

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    		
        HttpSession httpSession = request.getSession();
        ModelMap modelMap = modelAndView.getModelMap();
        Object memberVO =  modelMap.get("memberVO");
        
        
        if (memberVO != null) {
            httpSession.setAttribute(LOGIN, memberVO);
            if(request.getParameter("useCookie")!=null) {
            	Cookie loginCookie = new Cookie("loginCookie",httpSession.getId());
            	loginCookie.setPath("/");
            	loginCookie.setMaxAge(60*60*24*7);
            	response.addCookie(loginCookie);
            }
            
            Object destination = httpSession.getAttribute("destination");
            Object URL = httpSession.getAttribute("URL");
            response.sendRedirect(destination != null ? (String) destination : (String) URL);
        }

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession httpSession = request.getSession();
        // 기존의 로그인 정보 제거
        if (httpSession.getAttribute(LOGIN) != null) {
            httpSession.removeAttribute(LOGIN);
        }

        return true;
    }
}