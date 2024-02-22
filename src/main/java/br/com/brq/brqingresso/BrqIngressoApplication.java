package br.com.brq.brqingresso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BrqIngressoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrqIngressoApplication.class, args);
	}

}
