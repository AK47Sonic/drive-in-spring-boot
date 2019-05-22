package com.sonic.controller;

import com.sonic.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * MyExceptionHandler
 *
 * @author Sonic
 * @since 2019/5/22
 */
@ControllerAdvice
public class MyExceptionHandler {

    // JSON
//    @ResponseBody
//    @ExceptionHandler(UserNotExistException.class)
//    public Map<String, Object> handleException(Exception e){
//        Map<String, Object> map = new HashMap<>();
//        map.put("code", "user not exit");
//        map.put("message", e.getMessage());
//        return map;
//
//    }


    @ExceptionHandler(UserNotExistException.class)
    public String handleException(Exception e, HttpServletRequest request){

//        Integer statusCode  = (Integer) request.getAttribute("javax.servlet.error.status_code");
        request.setAttribute("javax.servlet.error.status_code", 500);

        Map<String, Object> map = new HashMap<>();
        map.put("code", "user not exit");
        map.put("message", "出错");
        request.setAttribute("ext", map);
        return "forward:/error";
    }


}
