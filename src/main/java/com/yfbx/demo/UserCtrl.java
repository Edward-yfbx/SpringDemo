package com.yfbx.demo;

import com.yfbx.demo.bean.User;
import com.yfbx.demo.dao.UserDao;
import com.yfbx.demo.json.JsonUtils;
import com.yfbx.demo.util.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserCtrl {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(String account, String password) {
        if (account == null || account.equals("")) {
            return JsonUtils.fail("用户名不能为空");
        }
        if (password == null || password.equals("")) {
            return JsonUtils.fail("密码不能为空");
        }

        User user = UserDao.find(account, password);//密码加密
        if (user == null) {
            return JsonUtils.fail("用户不存在或密码不正确");
        }
        return JsonUtils.success("登录成功", user);
    }


    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public String addUser(String account, String name, String password) {
        if (account == null || account.equals("")) {
            return JsonUtils.fail("账户不能为空");
        }
        if (name == null || name.equals("")) {
            return JsonUtils.fail("名称不能为空");
        }
        if (password == null || password.equals("")) {
            return JsonUtils.fail("密码不能为空");
        }

        User user = new User(Utils.createId(), name, account, password);
        boolean isSuccess = UserDao.insert(user);
        
        return isSuccess ? JsonUtils.success("新增用户成功") : JsonUtils.error("新增用户失败");
    }


}
