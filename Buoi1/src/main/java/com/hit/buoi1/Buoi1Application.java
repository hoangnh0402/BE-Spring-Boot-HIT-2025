package com.hit.buoi1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Buoi1Application {

	public static void main(String[] args) {
		// Khởi động ứng dụng Spring Boot
		ApplicationContext context = SpringApplication.run(Buoi1Application.class, args);

		// Lấy đối tượng User từ Container
		User user = context.getBean(User.class);
		user.makeCall();
	}
}

