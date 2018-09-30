package com.wuxue.api;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author chao.yu
 */
@SpringBootApplication
// @Configuration
// @ComponentScan
@EnableAutoConfiguration
@MapperScan("com.wuxue.api.mapper")
@EnableScheduling
public class CardApiApplication extends WebMvcConfigurerAdapter {

	public static Log logger = LogFactory.getLog(CardApiApplication.class);
	private static ApplicationContext ctx;

	public static ApplicationContext getInstance() {
		if (ctx == null) {
			ctx = SpringApplication.run(CardApiApplication.class, new String[] {});
		}
		return ctx;
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/**")
				.allowCredentials(true)
				.allowedHeaders("*")
				.allowedOrigins("*")
				.allowedMethods("*");

	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(CardApiApplication.class, args);
		/*ctx = SpringApplication.run(ApiApplication.class, args);

		logger.debug("Let's inspect the beans provided by Spring Boot:");
		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			// System.out.println(beanName);
		}
		System.out.println("startUp done");
		logger.info("startUp done");*/
	}

}
