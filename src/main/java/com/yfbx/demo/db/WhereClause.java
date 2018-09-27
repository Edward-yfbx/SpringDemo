package com.yfbx.demo.db;

import java.util.List;
import java.util.Map;

public class WhereClause {

    private StringBuilder builder;

    public WhereClause() {
        builder = new StringBuilder();
    }


    public WhereClause where(String column) {
        builder.append(" WHERE ").append(column);
        return this;
    }

    public WhereClause equal(Object value) {
        builder.append(" = ").append("'").append(value).append("'");
        return this;
    }

    public WhereClause like(Object value) {
        builder.append(" LIKE ").append("'%").append(value).append("%'");
        return this;
    }

    /**
     * @param operator 操作符， eg. > <  >=  <= !=
     */
    public WhereClause compare(String operator, Object value) {
        builder.append(" ")
                .append(operator)
                .append(" ")
                .append("'")
                .append(value)
                .append("'");
        return this;
    }


    public WhereClause and(String column) {
        builder.append(" AND ").append(column);
        return this;
    }

    public WhereClause or(String column) {
        builder.append(" OR ").append(column);
        return this;
    }

    public WhereClause between(Object val1, Object val2) {
        builder.append(" BETWEEN ").append(val1).append(" AND ").append(val2);
        return this;
    }


    public WhereClause limit(int limit) {
        builder.append(" LIMIT ").append(limit);
        return this;
    }

    public WhereClause offset(int offset) {
        builder.append(" OFFSET ").append(offset);
        return this;
    }

    public WhereClause orderASC(String... columns) {
        builder.append(" ORDER BY ");
        for (int i = 0; i < columns.length; i++) {
            builder.append(columns[i]);
            if (i != columns.length - 1) {
                builder.append(",");
            }
        }
        builder.append(" ASC ");
        return this;
    }

    public WhereClause orderDESC(String... columns) {
        builder.append(" ORDER BY ");
        for (int i = 0; i < columns.length; i++) {
            builder.append(columns[i]);
            if (i != columns.length - 1) {
                builder.append(",");
            }
        }
        builder.append(" DESC ");
        return this;
    }

    /**
     * SELECT * FROM table WHERE column IS NULL;
     */
    public WhereClause isNull() {
        builder.append("IS NULL ");
        return this;
    }

    /**
     * SELECT * FROM table WHERE column IS NOT NULL;
     */
    public WhereClause isNotNull() {
        builder.append("IS NOT NULL ");
        return this;
    }

    /**
     * 正则表达式
     * <p>
     * eg. 查找name字段中以'st'为开头的所有数据：
     * SELECT * FROM person WHERE name REGEXP '^st';
     */
    public WhereClause regexp(String regexp) {
        builder.append("'").append(regexp).append("'");
        return this;
    }


    /**
     * 查找
     */
    public <T> List<T> find(Class<T> clazz) {
        String tableName = clazz.getSimpleName().toLowerCase();
        String sql = "SELECT * FROM " + tableName + builder;
        return DbConnection.getInstance().exeQuery(sql, clazz);
    }

    /**
     * 查找
     */
    public <T> T findFirst(Class<T> clazz) {
        String tableName = clazz.getSimpleName().toLowerCase();
        String sql = "SELECT * FROM " + tableName + builder;
        List<T> list = DbConnection.getInstance().exeQuery(sql, clazz);
        if (list == null || list.size() <= 0) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 删除
     */
    public <T> boolean delete(Class<T> clazz) {
        String tableName = clazz.getSimpleName().toLowerCase();
        String sql = "DELETE * FROM " + tableName + builder;
        return DbConnection.getInstance().exeSql(sql);
    }

    /**
     * 修改
     */
    public <T> boolean update(Map<String, Object> map, Class<T> clazz) {
        String tableName = clazz.getSimpleName().toLowerCase();
        StringBuilder set = new StringBuilder();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            set.append(entry.getKey()).append("=").append("'").append(entry.getValue()).append("',");
        }
        set.deleteCharAt(set.length() - 1);
        String sql = "UPDATE " + tableName + " SET " + set + builder;
        return DbConnection.getInstance().exeSql(sql);
    }


}
