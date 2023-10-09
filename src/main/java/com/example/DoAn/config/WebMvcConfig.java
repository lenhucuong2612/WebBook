package com.example.DoAn.config;

import com.example.DoAn.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private UserService userService;

    public WebMvcConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addWebRequestInterceptor(new UserInterceptor(userService));
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Đặt địa chỉ gốc của các yêu cầu được chấp nhận (hoặc cụ thể cho từng nguồn).
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Đặt phương thức HTTP được chấp nhận.
                .allowedHeaders("*"); // Đặt tiêu đề HTTP được chấp nhận.

    }
}
