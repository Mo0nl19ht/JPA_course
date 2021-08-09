package jpabook.jpashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpashopApplication {


    public static void main(String[] args) {

        hello hello = new hello();
        hello.setData("hi");
        String data = hello.getData();


        SpringApplication.run(JpashopApplication.class, args);
    }

}
