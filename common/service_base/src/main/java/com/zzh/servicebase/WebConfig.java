package com.zzh.servicebase;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by zzh on 是啥
 */
//@SpringBootConfiguration
public class WebConfig implements WebMvcConfigurer {
    //    @Bean
//    public LoginInterceptor loginInterceptor() {
//        return new LoginInterceptor();
//    }

//    /**
//     * 加入自定义的拦截器
//     * @param registry
//     */
//    @Override
//    protected void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginInterceptor())
//                .addPathPatterns("/**").excludePathPatterns("/wxa/**")
//                .excludePathPatterns("/error").excludePathPatterns("/robot/**")
//                .excludePathPatterns("/swagger-resources/**")
//                .excludePathPatterns("/webjars/**").excludePathPatterns("/swagger*/**")
//                .excludePathPatterns("/actuator/*").excludePathPatterns("/pay/**")
//                .excludePathPatterns("/collegeOrder/**").excludePathPatterns("/user/isNeedLogin");
//    }

    /**
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //这里加handler是为了能使swagger功能及页面正常显示
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
