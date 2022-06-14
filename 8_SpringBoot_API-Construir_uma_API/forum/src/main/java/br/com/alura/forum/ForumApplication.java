package br.com.alura.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;


@EnableSpringDataWebSupport //Abilita que os campos de paginação e ordenação sejam pegos nos parametros da requisição na url
@EnableCaching
@SpringBootApplication
public class ForumApplication  extends SpringBootServletInitializer {


	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}

}


