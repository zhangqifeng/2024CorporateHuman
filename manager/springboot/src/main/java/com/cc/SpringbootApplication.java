package com.cc;

import com.cc.service.EmployeeService;
import com.cc.utils.TokenUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@SpringBootApplication
@MapperScan("com.cc.mapper")
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

    @Configuration
    public class AppConfig {
        @Autowired
        private EmployeeService employeeService;

        @PostConstruct
        public void init() {
            TokenUtils.setEmployeeService(employeeService);
        }
    }

}
