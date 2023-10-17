package com.test.postgresql_test.config;

import com.test.postgresql_test.config.auth.PrincipalDetailsService;
import com.test.postgresql_test.config.jwt.JwtAccessDeniedHandler;
import com.test.postgresql_test.config.jwt.JwtAuthenticationEntryPoint;
import com.test.postgresql_test.config.jwt.TokenProvider;
import com.test.postgresql_test.filter.CustomJwtFilter;
import com.test.postgresql_test.filter.SecurityTestFilter;
import com.test.postgresql_test.handler.CustomAuthFailureHandler;
import com.test.postgresql_test.handler.CustomAuthSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.session.DisableEncodeUrlFilter;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소 접근 시 권한 미리 체크
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PrincipalDetailsService principalDetailsService;
    private final CorsFilter corsFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final CustomJwtFilter customJwtFilter;
    private final TokenProvider tokenProvider;

    @Bean
    public BCryptPasswordEncoder pwdEncorder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring()
                .antMatchers("/favicon.ico");
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //JWT용
//        http
//                .httpBasic().disable()
//                .csrf().disable()
//                .formLogin().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/auth/**").permitAll()
//                .antMatchers("/js/**").permitAll()
//                .antMatchers("/css/**").permitAll()
//                .antMatchers("/").permitAll()
//                .antMatchers("/board/**").hasRole("USER")
//                .anyRequest().authenticated()
//                .and()
//                // filter 등록
//                // 매 요청마다
//                // CorsFilter 실행한 후에
//                // jwtAuthenticationFilter 실행한다.
//                .addFilterAfter(
//                    customJwtFilter,
//                    CorsFilter.class
//                );
////                .addFilterBefore(new CustomJwtFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);
//        return http.build();





//        httpSecurity
//                // token을 사용하는 방식이기 때문에 csrf를 disable합니다.
//                .csrf().disable()
//
//                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
//
//                .exceptionHandling()
//                .authenticationEntryPoint(jwtAuthenticationEntryPoint) // 우리가 만든 클래스로 인증 실패 핸들링
//                .accessDeniedHandler(jwtAccessDeniedHandler) // 커스텀 인가 실패 핸들링
//
//                // enable h2-console // embedded h2를 위한 설정
//                .and()
//                .headers()
//                .frameOptions()
//                .sameOrigin()
//
//                // 세션을 사용하지 않기 때문에 STATELESS로 설정
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//
//
//                .and()
//                .authorizeRequests()
//                    .antMatchers("/auth/**").permitAll()
//                    .anyRequest().authenticated()

//                .and()
//                .authorizeHttpRequests()
//                .requestMatchers("/h2/**").permitAll()
//                .requestMatchers("/favicon.ico").permitAll()
//                .requestMatchers("/error").permitAll()
//
//                // api 경로
//                .and()
//                .authorizeHttpRequests()
//                .requestMatchers("/api/hello").permitAll() // /api/hello
//                .requestMatchers("/api/v1/accounts/token").permitAll() // 로그인 경로
//                .requestMatchers("/api/v1/members").permitAll() // 회원가입 경로는 인증없이 호출 가능
//                .anyRequest().authenticated() // 나머지 경로는 jwt 인증 해야함

//                .and()
//                .addFilterBefore(new CustomJwtFilter(), UsernamePasswordAuthenticationFilter.class);
//
//        return httpSecurity.build();
//    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()

                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)

                .authorizeRequests()
                .antMatchers("/cfr").hasRole("USER")
                .anyRequest().permitAll()



                .and()
                .formLogin()
                .loginPage("/auth/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/auth/loginProc")
                .successHandler(new CustomAuthSuccessHandler())
//                .failureHandler(new CustomAuthFailureHandler())
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/");



        http.addFilterAfter(new SecurityTestFilter(), UsernamePasswordAuthenticationFilter.class);

    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailsService).passwordEncoder(pwdEncorder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
