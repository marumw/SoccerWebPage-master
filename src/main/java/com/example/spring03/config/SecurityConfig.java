package com.example.spring03.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.spring03.service.MemberSecurityService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
// 컨트롤러 메서드들에서 @PreAuthorize, @PostAuthorize 애너테이션을 이용할 수 있도록.
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration // 스프링 부트 앱 환경 설정(configuration)을 자바 코드로 설정.
public class SecurityConfig {
    
    @Bean // 스프링 컨텍스트에서 생성, 관리하는 객체 - 필요한 곳에 의존성 주입.
    // 암호화(복호화) 알고리즘 객체 -> Spring Security에서는 비밀번호는 반드시 암호화를 해야 함.
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
        http.csrf().disable();
        http.formLogin()
                .loginPage("/member/login")
                .defaultSuccessUrl("/")
            .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);
        
        http.authorizeHttpRequests()
        .antMatchers("/view/create?category=Notice", "/member/list").hasRole("ADMIN")
        .antMatchers("/view/**", "/api/**", "/member/modify/**", "/member/delete/**", "/member/detail/**").authenticated()
        .anyRequest().permitAll();
        
        return http.build();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}