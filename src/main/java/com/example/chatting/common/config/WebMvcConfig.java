package com.example.chatting.common.config;

import com.example.chatting.common.interceptor.ApiInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final ApiInterceptor apiInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiInterceptor) // 적용할 인터셉터 지정
                .addPathPatterns("/v2/api/**")  //인터셉터 적용 URL 경로 지정
                .excludePathPatterns("/v2/api/account/**", "/v2/api/items/**"); // 인터셉터를 적용하지 않을 URL 지정

    }
}
