package com.example.MiniProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // Import
import org.springframework.security.crypto.password.PasswordEncoder; // Import
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Bean này để mã hóa mật khẩu. Spring Security bắt buộc phải có.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean này tạo ra 2 người dùng mẫu (in-memory)
     * - admin/pass123 (Có quyền ADMIN và USER)
     * - user/pass123 (Chỉ có quyền USER)
     */
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails adminUser = User.builder()
            .username("admin")
            .password(passwordEncoder().encode("pass123")) // Mã hóa mật khẩu
            .roles("ADMIN", "USER") // Quyền
            .build();

        UserDetails regularUser = User.builder()
            .username("user")
            .password(passwordEncoder().encode("pass123")) // Mã hóa mật khẩu
            .roles("USER")
            .build();

        return new InMemoryUserDetailsManager(adminUser, regularUser);
    }

    /**
     * Bean này cấu hình các quy tắc bảo mật (Ai được truy cập cái gì)
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                // Yêu cầu (Lab 9): USER chỉ được xem
                .requestMatchers("/employees/list", "/api/employees").hasRole("USER") 
                // Yêu cầu (Lab 9): ADMIN được CRUD
                .requestMatchers("/employees/add", "/employees/save", "/api/employees/**").hasRole("ADMIN")
                // Cho phép tất cả truy cập Actuator
                .requestMatchers("/actuator/**").permitAll() 
                // Tất cả các request khác đều phải xác thực
                .anyRequest().authenticated() 
            )
            .formLogin((form) -> form
//                .loginPage("/login") // Sử dụng trang login tùy chỉnh (nếu có) hoặc trang mặc định
                .defaultSuccessUrl("/employees/list", true) // Chuyển đến trang này sau khi login
                .permitAll()
            )
            .logout((logout) -> logout.permitAll());

        return http.build();
    }
}
