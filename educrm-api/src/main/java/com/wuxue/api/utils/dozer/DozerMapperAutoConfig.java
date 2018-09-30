package com.wuxue.api.utils.dozer;

import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * PO--->VO 转换工具类
 */
@Configuration
public class DozerMapperAutoConfig {
    /**
     * xml 配置文件 路径
     */
    @Value("${dozer.mappingFiles}")
    private String[] mappingFiles;

    @Bean("dozerMapper")
    DozerBeanMapperFactoryBean dozerMapper() {
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        List<Resource> resources = new ArrayList<Resource>();
        if (mappingFiles != null) {
            for (String mapperLocation : mappingFiles) {
                try {
                    Resource[] mappers = resourceResolver.getResources(mapperLocation);
                    resources.addAll(Arrays.asList(mappers));
                } catch (IOException e) {
                    // ignore
                }
            }
        }
        DozerBeanMapperFactoryBean dozerMapper = new DozerBeanMapperFactoryBean();
        dozerMapper.setMappingFiles(resources.toArray(new Resource[resources.size()]));
        return dozerMapper;
    }
}
