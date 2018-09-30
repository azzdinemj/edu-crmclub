package com.wuxue;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author chao.yu
 */
@SpringBootApplication
//@Configuration
// @ComponentScan
@EnableAutoConfiguration
public class Applicationclong {

	public static Log logger = LogFactory.getLog(Applicationclong.class);
	private static ApplicationContext ctx;

	public static ApplicationContext getInstance() {
		if (ctx == null) {
			ctx = SpringApplication.run(Applicationclong.class, new String[] {});
		}
		return ctx;
	}

	public static void main(String[] args) {
		ctx = SpringApplication.run(Applicationclong.class, args);
		logger.debug("Let's inspect the beans provided by Spring Boot:");
		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			// System.out.println(beanName);
		}

		System.out.println("startUp done");
		logger.info("startUp done");
	}

}
