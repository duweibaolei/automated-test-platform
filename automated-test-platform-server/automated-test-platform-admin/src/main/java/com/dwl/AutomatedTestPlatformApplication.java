package com.dwl;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 应用启动类
 * Application Main Class
 * <p>
 * 双模式驱动的Web自动化测试平台服务端入口
 * </p>
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-09-03 13:11
 */
@SpringBootApplication
@MapperScan("com.dwl.dao.*.mapper")
@OpenAPIDefinition(info = @Info(
        title = "Automated Test Platform Server API",
        version = "1.0.0",
        description = """
                双模式驱动的Web自动化测试平台 API
                """
))
public class AutomatedTestPlatformApplication {

    /**
     * 应用入口方法
     *
     * @param args 命令行参数
     *             Command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(AutomatedTestPlatformApplication.class, args);
    }

}
