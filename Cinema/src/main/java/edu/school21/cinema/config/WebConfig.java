package edu.school21.cinema.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@PropertySource("classpath:../application.properties")
@ComponentScan(basePackages = "edu.school21.cinema")
@EnableWebMvc
public class WebConfig {

}
