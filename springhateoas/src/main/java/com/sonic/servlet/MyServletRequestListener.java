package com.sonic.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * MyServletRequestListener
 *
 * @author Sonic
 * @since 2019/8/4
 */
@WebListener
public class MyServletRequestListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        HttpServletRequest servletRequest = (HttpServletRequest)sre.getServletRequest();
        ServletContext servletContext = servletRequest.getServletContext();
        servletContext.log("request was requestDestroyed");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest servletRequest = (HttpServletRequest)sre.getServletRequest();
        ServletContext servletContext = servletRequest.getServletContext();
        servletContext.log("request was requestInitialized");
    }
}
