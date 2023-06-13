package com.example.notebook.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry
        .addResourceHandler("/static/**")
        .addResourceLocations("classpath:/static/");
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/index").setViewName("/home");
    registry.addViewController("/").setViewName("/home");
    registry.addViewController("/home").setViewName("/home");
    registry.addViewController("/register").setViewName("/register");
    registry.addViewController("/restore").setViewName("/restore");
    registry.addViewController("/forbidden").setViewName("/error/forbidden");
    registry.addViewController("/notfound").setViewName("/error/notfound");
    registry.addViewController("/pages/notebook").setViewName("/pages/notebook");
  }


}
