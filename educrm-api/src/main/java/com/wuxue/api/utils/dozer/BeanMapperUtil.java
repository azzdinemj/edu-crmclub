package com.wuxue.api.utils.dozer;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.*;

public class BeanMapperUtil {

    @Autowired
    @Qualifier("dozerMapper")
    protected Mapper mapper;

    public static final String MAPPING_SIMPLE_COURSE_XML = "dozer-bean-mapping/commonMapping.xml";

    public <T> T toObject(Object source, Class<T> type) {
        return source == null ? null : mapper.map(source, type);
    }

    //走自定义mapper.xml文件
    public <T> T toObject(Object source, Class<T> type, String mapperName) {
        if (StringUtils.isBlank(mapperName)) {
            return null;
        }

        DozerBeanMapper selfMapper = new DozerBeanMapper();
        selfMapper.setMappingFiles(Arrays.asList(mapperName));
        return source == null ? null : selfMapper.map(source, type);
    }

    public <T> List<T> toList(List source, Class<T> type) {
        if (CollectionUtils.isNotEmpty(source)) {
            List<T> resultList = new ArrayList<T>();
            for (Object o : source) {
                resultList.add(toObject(o, type));
            }
            return resultList;
        }
        return null;
    }

    //走自定义mapper.xml文件
    public <T> List<T> toList(List source, Class<T> type, String mapperName) {
        if (CollectionUtils.isEmpty(source) || StringUtils.isBlank(mapperName)) {
            return null;
        }

        List<T> resultList = new ArrayList<T>();
        for (Object o : source) {
            resultList.add(toObject(o, type, mapperName));
        }

        return resultList;
    }

    public <T, K> Map<String, T> toMap(Map<String, K> source, Class<T> type) {
        Map<String, T> newMap = new HashMap<String, T>();
        if (MapUtils.isNotEmpty(source)) {
            for (Map.Entry<String, K> entry : source.entrySet()) {
                newMap.put(entry.getKey(), toObject(entry.getValue(), type));
            }
        }
        return newMap;
    }

    public <T> T toObject(Object source, Class<T> type, Mapper mapper) {
        if (source == null || mapper == null) {
            return null;
        }

        return mapper.map(source, type);
    }

    public <T> List<T> toList(List source, Class<T> type, Mapper mapper) {
        if (source == null) {
            return null;
        }

        if (CollectionUtils.isEmpty(source)) {
            return new ArrayList<T>(0);
        }

        List<T> resultList = new ArrayList<T>();
        for (Object o : source) {
            resultList.add(toObject(o, type, mapper));
        }

        return resultList;
    }

}
