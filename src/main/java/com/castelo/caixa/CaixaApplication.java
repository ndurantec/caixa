package com.castelo.caixa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CaixaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaixaApplication.class, args);
	}

}
	