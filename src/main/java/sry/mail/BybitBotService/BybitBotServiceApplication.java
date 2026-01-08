package sry.mail.BybitBotService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BybitBotServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BybitBotServiceApplication.class, args);
	}

}
