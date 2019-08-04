package com.sonic.spring.boot;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * MyServlet2
 *
 * @author Sonic
 * @since 2019/8/4
 */
public class MyServlet2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();
        ServletContext servletContext = getServletContext();
//        ServletContext servletContext = req.getServletContext();
        servletContext.log("myServlet2 doGet ...");
        writer.write("<html><body>Hello, World from myServlet2</body></html>");
    }

}
