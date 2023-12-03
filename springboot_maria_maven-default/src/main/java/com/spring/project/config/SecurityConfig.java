package com.spring.project.config;
import java.io.IOException;
import java.util.Arrays;

import javax.security.sasl.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.spring.project.handler.CustomAuthFailureHandler;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    AuthenticationFailureHandler customAuthFailureHandler(){
        return new CustomAuthFailureHandler();
    }    

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/resource/**", "/login", "/joinPage", "/joinProc", "/login-error").permitAll() /*인증이 필요없는 정적 데이터*/
                //.antMatchers("/main").hasRole("ROLE_ADMIN")
                //.antMatchers("/main").hasRole("ROLE_USER") //.antMatchers("/main").access("hasRole('ROLE_ADMIN')") 와 같음
                //.antMatchers("/main").access("hasRole('ROLE_AMDIN')")
                //.antMatchers("/main").access("hasRole('ROLE_USER')")
                .anyRequest().authenticated() /* 그 외 모든 요청은 인증된 사용자만 접근이 가능하게 처리*/
 
        .and().formLogin()
                .loginPage("/loginPage")
        		.loginProcessingUrl("/loginProc")
        		.defaultSuccessUrl("/main", true) /*로그인 성공시 url*/
                .failureUrl("/loginPage?error") /*로그인 실패시 url*/
                .usernameParameter("userId")
                .passwordParameter("password")
                .failureHandler(customAuthFailureHandler()) // 로그인 실패 핸들러
                .permitAll()
        .and().logout()
                .logoutUrl("/logout")  /*로그아웃 url*/
                .logoutSuccessUrl("/loginPage")  /*로그아웃 성공시 연결할 url*/
                .permitAll()
        .and().csrf().disable();
    }
    
    /*
	@Bean("preProcessFilter")
	Filter preProcessFilter() {
		return new PreProcessFilter();
	}*/
	
    /*
	@Bean("jwtAuthenticationFilter")
	Filter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	} */   

    // TODO Security 설정 사용자의 유저네임과 패스워드가 맞는지 검증해준다.
	/*
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }*/
	
	/**
	 * Cors configuration source cors configuration source. Cors 허용 옵션 설정 부분
	 *
	 * @return the cors configuration source
	 */
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "TOKEN_ID", "X-Requested-With", "Authorization",
				"Content-Type", "Content-Length", "Cache-Control"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	

}