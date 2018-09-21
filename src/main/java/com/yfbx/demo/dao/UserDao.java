package com.yfbx.demo.dao;

import com.yfbx.demo.bean.User;
import com.yfbx.demo.db.DbUtil;

public class UserDao {

    public static User find(String account, String password) {
        return DbUtil.where("account").equal(account)
                .and("password").equal(password)
                .findFirst(User.class);
    }

    public static boolean insert(User user) {
        return DbUtil.insert(user);
    }

}
