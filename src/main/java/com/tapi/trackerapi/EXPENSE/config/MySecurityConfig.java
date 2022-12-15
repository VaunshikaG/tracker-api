package com.tapi.trackerapi.EXPENSE.config;

import com.tapi.trackerapi.EXPENSE.jwt.CustomUserDetailService;
import com.tapi.trackerapi.EXPENSE.jwt.JwtAuthenticationEntryPoint;
import com.tapi.trackerapi.EXPENSE.jwt.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebMvc
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    public static final String[] PUBLIC_URLS = {
            "/expense/user/login",
            "/expense/user/register",
            "/expense/users",
            "/v3/api-docs",
            "/v2/api-docs",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/webjars/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers(PUBLIC_URLS).permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;

//        http.authorizeRequests()
//                .antMatchers("/expense/login").permitAll()
//                .antMatchers("/expense/user/register").permitAll()
//                .anyRequest().authenticated();
//
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(this.jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.customUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
