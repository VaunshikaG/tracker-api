package com.tapi.trackerapi.NEW;
import com.tapi.trackerapi.NEW.HELPER.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories
public class NEW {

    public static void main(String[] args) {
        SpringApplication.run(NEW.class, args);
    }

    @Bean
    public FilterRegistrationBean<AuthFilter> filterRegistrationBean () {
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
        AuthFilter authFilter = new AuthFilter();
        registrationBean.setFilter(authFilter);
        registrationBean.addUrlPatterns("/tracker/category/*");
        return registrationBean;
    }
}
