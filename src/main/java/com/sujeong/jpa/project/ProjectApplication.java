package com.sujeong.jpa.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableJpaAuditing // BaseEntity클래스의 AuditingEntityListener를 활성화 시키기 위해 추가한다
@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@RestController
	class HelloWorld{
		@GetMapping("/")
		public String test(){
			return "Hello";
		}
	}

}
