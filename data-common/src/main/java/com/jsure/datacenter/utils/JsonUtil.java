package com.jsure.datacenter.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wuxiaobiao
 * @Description:
 * @Date: Created in 2018/4/10
 * @Time: 18:08
 * I am a Code Man -_-!
 */
@Slf4j
public class JsonUtil {

    private static ObjectMapper objectMapper = null;

    static {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss") {
            });
        }
    }

    private JsonUtil() {

    }

    public static ObjectMapper getInstance() {

        return objectMapper;
    }

    /**
     * javaBean,list,array convert to json string
     */
    public static String obj2json(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("failed to convert obj to json!reason:{}", e.getStackTrace());
            throw new RuntimeJsonMappingException("wrong parameter!");
        }
    }

    /**
     * json string convert to javaBean
     */
    public static <T> T json2pojo(String jsonStr, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonStr, clazz);
        } catch (IOException e) {
            log.error("fail to convert json to pojo!reason{}", e.getStackTrace());
            e.printStackTrace();
            throw new RuntimeJsonMappingException("IOException!");
        }catch (Exception e ){
            e.printStackTrace();
            throw  e;
        }
    }

    /**
     * json string convert to map
     */
    public static <T> Map<String, Object> json2map(String jsonStr) {
        try {
            return objectMapper.readValue(jsonStr, Map.class);
        } catch (IOException e) {
            log.error("fail to covert json to map!reason{}", e.getStackTrace());
            throw new RuntimeJsonMappingException("IOException!");
        }
    }

    /**
     * json string convert to map with javaBean
     */
    public static <T> Map<String, T> json2map(String jsonStr, Class<T> clazz) {

        Map<String, Map<String, Object>> map = null;
        try {
            map = objectMapper.readValue(jsonStr,
                    new TypeReference<Map<String, T>>() {
                    });
        } catch (IOException e) {
            log.error("fail to covert json to map!reason{}", e.getStackTrace());
            throw new RuntimeJsonMappingException("IOException!");
        }
        Map<String, T> result = new HashMap<String, T>();
        for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
            result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));
        }
        return result;
    }

    /**
     * json array string convert to list with javaBean
     */
    public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz) {
        List<Map<String, Object>> list = null;
        try {
            list = objectMapper.readValue(jsonArrayStr,
                    new TypeReference<List<T>>() {
                    });
            List<T> result = new ArrayList<T>();
            for (Map<String, Object> map : list) {
                result.add(map2pojo(map, clazz));
            }
            return result;
        } catch (IOException e) {
            log.error("fail to convert json to list!reason{}", e.getStackTrace());
            throw new RuntimeJsonMappingException("IOException！");
        }
    }

    /**
     * map convert to javaBean
     */
    public static <T> T map2pojo(Map map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }
}
