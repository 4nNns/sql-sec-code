package com.ha0l.sqlseccode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ha0
 * @date 2022-0823
 */
@SpringBootApplication
@RestController
@ServletComponentScan
@Slf4j
@EnableAsync
public class SqlSecCodeApplication {

    @GetMapping("/")
    public String base() {
        return "System Up and Running!";
    }

    public static void main(String[] args) {
        SpringApplication.run(SqlSecCodeApplication.class, args);
    }

}
