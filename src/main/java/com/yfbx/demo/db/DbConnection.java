package com.yfbx.demo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class DbConnection {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/demo";
    private static final String USER = "root";
    private static final String PASS = "123456";


    /**
     * 执行查询语句
     */
    public static <T> List<T> exeQuery(String sql, Class<T> clazz) {
        System.out.println(sql);
        try {
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 打开链接
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = connection.createStatement();
            //执行语句
            ResultSet rs = stmt.executeQuery(sql);
            List<T> result = Converter.toList(rs, clazz);
            rs.close();
            stmt.close();
            connection.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 执行sql语句
     */
    public static boolean exeSql(String sql) {
        System.out.println(sql);
        try {
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 打开链接
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = connection.createStatement();
            //执行语句
            stmt.execute(sql);
            stmt.close();
            connection.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
