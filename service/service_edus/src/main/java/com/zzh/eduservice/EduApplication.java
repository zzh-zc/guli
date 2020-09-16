package com.zzh.eduservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * Created on @2020/9/16 22:36
 *
 * @author zzh
 * @email zzhzc.cn
 * @since 1.0
 */
@MapperScan("com.zzh.eduservice.mapper")
@SpringBootApplication
@ComponentScan(basePackages = {"com.zzh"})
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
