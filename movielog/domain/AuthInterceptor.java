package com.movielog.domain;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter{

	    private void saveDestination(HttpServletRequest request) {
	    	String uri = request.getRequestURI();
	    	String query = request.getQueryString();
	    	if(query == null || query.equals("null")) {
	    		query = "";
	    	}else {
	    		query = "?"+query;
	    	}
	    	
	    	if(request.getMethod().equals("GET")) {
	    		request.getSession().setAttribute("destination", uri + query);
	    	}
	    }
	    
	    @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	    		
	    	HttpSession httpSession = request.getSession();
	    	
	    	if(httpSession.getAttribute("login") == null) {
	    		saveDestination(request);
	    		response.sendRedirect("/member/errorLogin");
	    		return false;
	    	}
	    	return true;
	    }
	}