package com.wuxue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

/**
 *
 * @author chao.yu
 */
@SpringBootApplication
//@Configuration
// @ComponentScan
@EnableAutoConfiguration
public class Applicationzhyou {
/*

	@Bean
	public HttpMessageConverter<String> responseBodyConverter() {
		StringHttpMessageConverter converter = new StringHttpMessageConverter(
				Charset.forName("UTF-8"));
		return converter;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		//处理中文乱码问题
		List<MediaType> fastMediaTypes = new ArrayList<>();
		fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		fastConverter.setSupportedMediaTypes(fastMediaTypes);
		fastConverter.setFastJsonConfig(fastJsonConfig);
		converters.add(fastConverter);
	}

	@Override
	public void configureContentNegotiation(
			ContentNegotiationConfigurer configurer) {
		configurer.favorPathExtension(false);
	}
*/

    public static Log logger = LogFactory.getLog(Applicationzhyou.class);
    private static ApplicationContext ctx;

    public static ApplicationContext getInstance() {
        if (ctx == null) {
            ctx = SpringApplication.run(Applicationzhyou.class, new String[] {});
        }
        return ctx;
    }

    public static void main(String[] args) {


        ctx = SpringApplication.run(Applicationzhyou.class, args);

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

