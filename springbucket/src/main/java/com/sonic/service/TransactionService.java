package com.sonic.service;

import com.sonic.bean.Student;
import com.sonic.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TransactionService
 *
 * @author Sonic
 * @since 2019/4/20
 */
@Service
public class TransactionService {

    @Autowired
    private StudentMapper studentMapper;

    public int insertStudent(){
        Student student = new Student();
        student.setId(1);
        student.setSname("Yuki");
        return studentMapper.insertStudent(student);
    }


}
