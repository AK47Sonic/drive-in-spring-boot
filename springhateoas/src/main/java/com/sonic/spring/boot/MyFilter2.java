package com.sonic.spring.boot;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * MyFilter2
 *
 * @author Sonic
 * @since 2019/8/4
 */
public class MyFilter2 extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//        ServletContext servletContext = request.getServletContext();
//        String requestURI = request.getRequestURI();
//        StringBuffer requestURL = request.getRequestURL();
//        servletContext.log(requestURI + "," + requestURL + " was filtered");


        doSomething();
        filterChain.doFilter(request, response);
    }

    public void doSomething(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String requestURI = request.getRequestURI();
        StringBuffer requestURL = request.getRequestURL();
        ServletContext servletContext = request.getServletContext();
        servletContext.log(requestURI + "," + requestURL + " was filtered");

    }

}
