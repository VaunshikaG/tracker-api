package com.tapi.trackerapi.EXPENSE;
import com.tapi.trackerapi.EXPENSE.HELPER.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories
public class TRACKER {

    public static void main(String[] args) {
        SpringApplication.run(TRACKER.class, args);
    }

//    @Bean
//    public FilterRegistrationBean<AuthFilter> filterRegistrationBean () {
//        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
//        AuthFilter authFilter = new AuthFilter();
//        registrationBean.setFilter(authFilter);
//        registrationBean.addUrlPatterns("/tracker/category/*");
//        return registrationBean;
//    }

}
