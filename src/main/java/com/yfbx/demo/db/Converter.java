package com.yfbx.demo.db;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Converter {

    /**
     * 将ResultSet转换成List<Map>
     */
    public static List<Map<String, Object>> toList(ResultSet rs) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>();
        ResultSetMetaData md = rs.getMetaData();//获取键名
        int columnCount = md.getColumnCount();//获取行的数量
        while (rs.next()) {
            Map<String, Object> rowData = new HashMap<>();//声明Map
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnName(i), rs.getObject(i));//获取键名及值
            }
            list.add(rowData);
        }
        return list;
    }

    /**
     * 将ResultSet转换成List<T>
     */
    public static <T> List<T> toList(ResultSet rs, Class<T> clazz) throws SQLException {
        List<T> list = new ArrayList<>();
        ResultSetMetaData md = rs.getMetaData();//获取键名
        int columnCount = md.getColumnCount();//获取行的数量
        while (rs.next()) {
            Map<String, Object> rowData = new HashMap<>();//声明Map
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnName(i), rs.getObject(i));//获取键名及值
            }
            list.add(toBean(rowData, clazz));
        }
        return list;
    }

    /**
     * Map转换成Bean
     */
    public static <T> T toBean(Map<String, Object> map, Class<T> clazz) {
        try {
            //创建JavaBean对象
            T bean = clazz.newInstance();
            //获取指定类的BeanInfo对象
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz, Object.class);
            //获取所有的属性描述器
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor pd : pds) {
                Object value = map.get(pd.getName());
                Method setter = pd.getWriteMethod();
                setter.invoke(bean, value);
            }
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * JavaBean转换为Map
     */
    public static <T> Map<String, Object> toMap(T bean) {
        try {
            Map<String, Object> map = new HashMap<>();
            //获取指定类（Person）的BeanInfo 对象
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass(), Object.class);
            //获取所有的属性描述器
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor pd : pds) {
                String key = pd.getName();
                Method getter = pd.getReadMethod();
                Object value = getter.invoke(bean);
                map.put(key, value);
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
