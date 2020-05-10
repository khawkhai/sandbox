package com.avisetech.helloworld;

import com.avisetech.helloworld.annotation.Animal;
import com.avisetech.helloworld.annotation.Creature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Map;

@SpringBootApplication
public class ComponentScanApplication {
	private static ApplicationContext applicationContext;

	public static void main(String[] args) {
		applicationContext = SpringApplication.run(ComponentScanApplication.class, args);
		getAllBean();
	}

	private static void getAllBean() {
		Map<String, Object> beans = applicationContext.getBeansWithAnnotation(Creature.class);

		beans.values().forEach(v -> {
			System.out.println(((Animal)v).getName());
		});
	}
}
