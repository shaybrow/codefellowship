package com.shaybrow.codefellowship.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


    @Configuration
    @EnableWebSecurity
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Autowired
        UserDetailsServiceImplementation userDetailsService;

        @Bean
        public PasswordEncoder passwordEncoder(){
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            return bCryptPasswordEncoder;

        }
        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Override
        public void configure(final AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        }



        @Override
        public void configure (HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                    .cors().disable()
                    .csrf().disable() //heroku needs this
//                    allowing access
                    .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/").permitAll()
                    .antMatchers("/signup", "/login", "/codeuser").permitAll()
                    .antMatchers(HttpMethod.GET, "/user/*").permitAll()
                    .antMatchers("/css/**").permitAll()
                    .anyRequest().authenticated()
//                  access denied returns to home route
                    .and().exceptionHandling().accessDeniedPage("/")

//                using and allows us to change a different set of parameters

//                    returns to
                    .and()
                    .formLogin().loginPage("/login").defaultSuccessUrl("/myprofile")
//                      allows basic logout functionality
                    .and()
                    .logout()
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")

            ;

        }
    }


