package com.project.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.filtre.CustomAuthenticationFilter;
import com.project.filtre.CustomAuthorizationFilter;
import static org.springframework.http.HttpMethod.PUT;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.security.config.http.SessionCreationPolicy.*;


@Configuration @EnableWebSecurity @RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers("/api/login/**", "/api/token/refresh/**").permitAll();
        http.authorizeRequests().antMatchers(POST, "/api/role/**").permitAll();

        http.authorizeRequests().antMatchers(GET, "/api/users/**").permitAll();/*.hasAnyAuthority("ROLE_ADMIN","ROLE_SUPER_ADMIN");*/
        http.authorizeRequests().antMatchers(POST, "/api/user/save").permitAll();
        http.authorizeRequests().antMatchers(DELETE, "/api/user/save/**").permitAll();
        http.authorizeRequests().antMatchers(PUT, "api/modify-client/**").hasAnyAuthority("ROLE_ADMIN");

        
        http.authorizeRequests().antMatchers(GET, "/api/employees").permitAll();/*.hasAnyAuthority("ROLE_ADMIN","ROLE_SUPER_ADMIN");*/
        http.authorizeRequests().antMatchers(GET, "/api/employee/**").permitAll();/*.hasAnyAuthority("ROLE_ADMIN","ROLE_SUPER_ADMIN");*/
        http.authorizeRequests().antMatchers(POST, "/api/employee/save").permitAll();
        http.authorizeRequests().antMatchers(POST, "/api/employee/update").permitAll();
        http.authorizeRequests().antMatchers(DELETE, "/api/employee/delete/**").permitAll();
        
        http.authorizeRequests().antMatchers(GET, "/api/companys").permitAll();/*.hasAnyAuthority("ROLE_ADMIN","ROLE_SUPER_ADMIN");*/
        http.authorizeRequests().antMatchers(GET, "/api/company/**").permitAll();/*.hasAnyAuthority("ROLE_ADMIN","ROLE_SUPER_ADMIN");*/
        http.authorizeRequests().antMatchers(POST, "/api/company/save").permitAll();
        http.authorizeRequests().antMatchers(POST, "/api/company/update").permitAll();
        http.authorizeRequests().antMatchers(DELETE, "/api/company/delete/**").permitAll();
        
        http.authorizeRequests().antMatchers(GET, "/api/invitations").permitAll();/*.hasAnyAuthority("ROLE_ADMIN","ROLE_SUPER_ADMIN");*/
        http.authorizeRequests().antMatchers(GET, "/api/invitation/**").permitAll();/*.hasAnyAuthority("ROLE_ADMIN","ROLE_SUPER_ADMIN");*/
        http.authorizeRequests().antMatchers(POST, "/api/invitation/save").permitAll();
        http.authorizeRequests().antMatchers(POST, "/api/invitation/update").permitAll();
        http.authorizeRequests().antMatchers(DELETE, "/api/invitation/delete/**").permitAll();
        
        http.authorizeRequests().antMatchers(POST, "/api/sendInvitation").permitAll();/*.hasAnyAuthority("ROLE_ADMIN","ROLE_SUPER_ADMIN");*/
        http.authorizeRequests().antMatchers(GET, "/api/sentInvitations").permitAll();/*.hasAnyAuthority("ROLE_ADMIN","ROLE_SUPER_ADMIN");*/
        http.authorizeRequests().antMatchers(GET, "/api/countInvitations").permitAll();
        
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    
}
