package com.tugasnegaraprojectjava;

import com.tugasnegaraprojectjava.restAPI.rabbitmqAPI.RestApiReceive;
import com.tugasnegaraprojectjava.restAPI.securoty.JWTAuthorizationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication(scanBasePackages = {"com."})
public class TugasNegaraProjectJavaApplication {

    public static void main(String[] args) {

        SpringApplication.run(TugasNegaraProjectJavaApplication.class, args);
        RestApiReceive restApiReceive = new RestApiReceive();
        try {
            restApiReceive.receiveFromDatabase();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}

@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/user/login").permitAll()
                .antMatchers(HttpMethod.POST, "/user/register").permitAll()
                .antMatchers(HttpMethod.POST, "/user/logout").permitAll()
                .antMatchers(HttpMethod.POST, "/user/forgot-password").permitAll()
                .antMatchers(HttpMethod.POST, "/user/reset-password/**").permitAll()
                .anyRequest().authenticated();
    }
}
