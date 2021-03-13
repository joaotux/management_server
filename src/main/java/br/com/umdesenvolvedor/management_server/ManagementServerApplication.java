package br.com.umdesenvolvedor.management_server;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ManagementServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagementServerApplication.class, args);
	}

	@Bean
	public ModelMapper modelMaper() {
		return new ModelMapper();
	}
}
