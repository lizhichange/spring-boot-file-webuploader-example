package com.webuploader.model;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CglibBeanTest {
    @Test
    public void test01() throws ClassNotFoundException {
        // 生成动态Bean  // 设置类成员属性
        Map<String, Class> propertyMap = new HashMap<>();
        propertyMap.put("id", Class.forName("java.lang.Integer"));
        propertyMap.put("name", Class.forName("java.lang.String"));
        propertyMap.put("address", Class.forName("java.lang.String"));
        propertyMap.put("date", Class.forName("java.sql.Date"));
        CglibBean bean = new CglibBean(propertyMap);
        // 给Bean设置值  //Auto-boxing
        bean.setValue("id", 123);
        bean.setValue("name", "454");
        bean.setValue("address", "789");
        // 从Bean中获取值，当然获得值的类型是Object
        System.out.println(" >> id = " + bean.getValue("id"));
        System.out.println(" >> name = " + bean.getValue("name"));
        System.out.println(" >> address = " + bean.getValue("address"));
        // 获得bean的实体
        Object object = bean.getObject();
        // 通过反射查看所有方法名
        Class clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method curMethod : methods) {
            System.out.println(curMethod.getName());
        }

        Object o = JSONObject.toJSON(object);
        System.out.println(o);


    }

    @Test
    public void test02() throws ClassNotFoundException {

        String CONTENT_CONFIG = "{\n" +
                "\t\"keyword3\": \"国内最火爆、最好玩的小程序尽在大咖游戏盒子\",\n" +
                " \t\"keyword2\": \"[内涵福利] 点击立刻前往探寻绝世武功秘籍，无需下载即点即玩\",\n" +
                "\t\"keyword1\": \"抢！秘籍\"\n" +
                "}";
        JSONObject jsonObject = JSONObject.parseObject(CONTENT_CONFIG);
        Map<String, Class> propertyMap = new HashMap<>();
        jsonObject.forEach((s, o1) -> {
            try {
                propertyMap.put(s, Class.forName("java.lang.String"));
            } catch (ClassNotFoundException e) {

            }
        });

        jsonObject.forEach((s, o1) -> System.out.println(s + "---" + o1));

        CglibBean bean = new CglibBean(propertyMap);
        jsonObject.forEach(bean::setValue);
        Object object = bean.getObject();
        System.out.println(object);
        Object o = JSONObject.toJSON(object);
        System.out.println(o);
    }


}
