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
    registry.addViewController("/index").setViewName("/anonymous-pages/home");
    registry.addViewController("/").setViewName("/anonymous-pages/home");
    registry.addViewController("/a/home").setViewName("/anonymous-pages/home");
    registry.addViewController("/a/register").setViewName("/anonymous-pages/register");
    registry.addViewController("/a/restore").setViewName("/anonymous-pages/restore");
    registry.addViewController("/e/forbidden").setViewName("/error/forbidden");
    registry.addViewController("/e/notfound").setViewName("/error/notfound");
    registry.addViewController("/u/notebook").setViewName("/user-pages/notebook");
  }

}
