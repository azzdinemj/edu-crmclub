/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wuxue.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
* @Description: 
* @author wanghao
* @date  10:37 2018/3/8
* @version V1.0
*/

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new SingonInterceptor()).addPathPatterns("/**");
	}
}