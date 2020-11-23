package com.lw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Main{
    //http://localhost:8080/test/hello
    //git push -u origin liwei -f
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }
}
