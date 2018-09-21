package com.yfbx.demo.db;

import java.util.List;
import java.util.Map;

public class DbUtil {

    /**
     * 新增
     */
    public static <T> boolean insert(T bean) {
        String tableName = bean.getClass().getSimpleName().toLowerCase();
        Map<String, Object> map = Converter.toMap(bean);
        if (map == null) {
            return false;
        }

        StringBuilder names = new StringBuilder();
        StringBuilder values = new StringBuilder();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            names.append(entry.getKey()).append(",");
            values.append("'").append(entry.getValue()).append("',");
        }
        names.deleteCharAt(names.length() - 1);
        values.deleteCharAt(values.length() - 1);

        String sql = "INSERT INTO " + tableName + "(" + names + ")" + "VALUES" + "(" + values + ")";
        return DbConnection.exeSql(sql);
    }

    /**
     * 由Where 语句进入条件查询，更新，删除
     */
    public static WhereClause where(String column) {
        return new WhereClause().where(column);
    }

    /**
     * 查找所有
     */
    public static <T> List<T> findAll(Class<T> clazz) {
        String tableName = clazz.getSimpleName().toLowerCase();
        String sql = "SELECT * FROM " + tableName + ";";
        return DbConnection.exeQuery(sql, clazz);
    }

    /**
     * 清空
     */
    public <T> boolean clear(Class<T> clazz) {
        String tableName = clazz.getSimpleName().toLowerCase();
        String sql = "DELETE * FROM " + tableName + ";";
        return DbConnection.exeSql(sql);
    }

}
