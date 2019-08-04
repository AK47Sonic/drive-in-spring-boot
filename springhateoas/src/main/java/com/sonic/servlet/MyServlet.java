package com.sonic.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * MyServlet
 *
 * @author Sonic
 * @since 2019/8/4
 */
@WebServlet(name = "myServlet", urlPatterns = {"/myservlet"},
        initParams = {@WebInitParam(name= "myname", value = "myvalue")}
)
public class MyServlet extends HttpServlet {

    private String value;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        value = config.getInitParameter("myname");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();
        ServletContext servletContext = getServletContext();
//        ServletContext servletContext = req.getServletContext();
        servletContext.log("myServlet doGet ...");
        writer.write("<html><body>Hello, my value: " + value + "</body></html>");
    }
}
