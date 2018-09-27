package com.yfbx.demo.db;

import java.sql.*;
import java.util.List;

public class DbConnection {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/demo";
    private static final String USER = "root";
    private static final String PASS = "123456";

    private static DbConnection instance;

    public static DbConnection getInstance() {
        if (instance == null) {
            synchronized (DbConnection.class) {
                if (instance == null) {
                    instance = new DbConnection();
                }
            }
        }
        return instance;
    }

    private DbConnection() {
    }

    /**
     * 连接数据库
     */
    private Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    /**
     * 执行查询语句
     */
    public <T> List<T> exeQuery(String sql, Class<T> clazz) {
        System.out.println(sql);
        try {
            Connection connection = connect();
            Statement stmt = connection.createStatement();
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
    public boolean exeSql(String sql) {
        System.out.println(sql);
        try {
            Connection connection = connect();
            Statement stmt = connection.createStatement();
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
