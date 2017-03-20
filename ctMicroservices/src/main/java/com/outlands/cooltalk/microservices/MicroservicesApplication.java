package com.outlands.cooltalk.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableAutoConfiguration
//@EnableJpaRepositories("com.outlands.cooltalk.ctEntities")
@EntityScan("com.outlands.cooltalk.ctEntities")
//@ComponentScan(basePackages={"com.outlands"})
@ImportResource({
	"classpath:/outlandsUtilityContext.xml",
	"classpath:/ctCommonContext.xml",
	"classpath:/ctEntitiesContext.xml"
	})
public class MicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesApplication.class, args);
	}
}
