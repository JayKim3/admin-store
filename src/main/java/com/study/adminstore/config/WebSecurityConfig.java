package com.study.adminstore.config;

import com.study.adminstore.service.MemberApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security를 활성
@Configuration
// 스프링 시큐리티를 사용하기 위하여 WebSecurityConfigurerAdapter 상속
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final MemberApiService memberApiService;
    private final DataSource dataSource;
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 정적리소스 무조건 접근 가능하도록 설정
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }

    // http 관련 인증 설정
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // post api csrf 차단
                // 페이지 권한설정
                .authorizeRequests()
                .antMatchers("/login", "/signup", "/emailCheck", "/find").permitAll()
                .antMatchers("/mypage").hasAnyRole("USER", "ADMIN")
                .antMatchers("/admin", "/mypage", "/master", "/user").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                // 로그인 관련 설정
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error=true")
                .and()
                // 로그아웃 관련 설정
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(customLogoutSuccessHandler)
                .and()
                .sessionManagement()
                .maximumSessions(1); // 최대 접속수를 1개로 제한한다.

        http.rememberMe()
                .userDetailsService(memberApiService)
                .tokenRepository(tokenRepository());
        // username, 토큰, 시리즈를 조합한 토큰 정보를 DB에 저장(rememberMe 쿠키랑 일치하는 지 확인하기 위함)
    }

    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    // 비밀번호 암호화 객체 사용
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberApiService).passwordEncoder(passwordEncoder()) ;
    }
}


