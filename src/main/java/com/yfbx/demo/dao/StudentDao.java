package com.yfbx.demo.dao;

import com.yfbx.demo.bean.Student;
import com.yfbx.demo.db.DbUtil;

import java.util.List;

public class StudentDao {

    public static List<Student> findAll() {
        return DbUtil.findAll(Student.class);
    }

    public static boolean insert(Student student) {
        return DbUtil.insert(student);
    }
}
