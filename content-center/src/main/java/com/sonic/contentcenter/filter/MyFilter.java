package com.sonic.contentcenter.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * MyFilter
 *
 * @author Sonic
 * @since 2020/5/2
 */
@Slf4j
@WebFilter(filterName = "myFilter", urlPatterns = "/shares/*")
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("--------------init---------------");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("--------------doFilter---------------");
        chain.doFilter(request, response); // 传递给下一个Filter进行处理
    }

    @Override
    public void destroy() {
        log.info("--------------destroy---------------");
    }
}
