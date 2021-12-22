package fr.fab.test;


import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@Log4j2
public class TestApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(TestApplication.class, args);
		if (log.isDebugEnabled()){
			Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(b -> log.debug(String.format("managed bean + %s :", b)));
		}
	}

}
