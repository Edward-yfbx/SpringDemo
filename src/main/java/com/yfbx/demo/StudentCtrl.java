package com.yfbx.demo;

import com.yfbx.demo.bean.Student;
import com.yfbx.demo.dao.StudentDao;
import com.yfbx.demo.json.JsonUtils;
import com.yfbx.demo.util.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class StudentCtrl {


    @RequestMapping(value = "/getStudents", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents() {
        List<Student> all = StudentDao.findAll();
        if (all == null || all.size() == 0) {
            return JsonUtils.fail("暂无数据");
        }
        return JsonUtils.success("请求成功", all);
    }


    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    @ResponseBody
    public String addStudent(String name, int age) {
        if (name == null || name.trim().equals("")) {
            return JsonUtils.fail("姓名不能为空");
        }
        if (age < 1 || age > 150) {
            return JsonUtils.fail("非法的年龄参数");
        }
        Student student = new Student(Utils.createId(), name, age);
        boolean isSuccess = StudentDao.insert(student);
        
        return isSuccess ? JsonUtils.success("新增成功") : JsonUtils.error("新增失败");
    }
}
