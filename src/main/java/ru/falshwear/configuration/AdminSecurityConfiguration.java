package ru.falshwear.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class AdminSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity config) throws Exception {
        config
                .authorizeRequests()
                .antMatchers("/photoPage").permitAll()
                .antMatchers("/adminPage").hasRole("ADMIN")
                .antMatchers("/adminPage/addProduct").hasRole("ADMIN")
                .antMatchers("/adminPage/seeProducts").hasRole("ADMIN")
                .and()
                .formLogin().loginPage("/loginPage").defaultSuccessUrl("/adminPage").permitAll()
                .and()
                .formLogin().loginPage("/loginPage").defaultSuccessUrl("/adminPage/addProduct").permitAll()
                .and()
                .formLogin().loginPage("/loginPage").defaultSuccessUrl("/adminPage/seeProducts").permitAll()
                .and()
                .logout().logoutUrl("/logoutPage").permitAll();
        config.csrf().disable().authorizeRequests().anyRequest().permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.inMemoryAuthentication()
                .withUser("Vilkin").password("{noop}nisk1234").roles("ADMIN")
                .and()
                .withUser("Динчян Дмитрий").password("{noop}115599").roles("ADMIN");
    }
}
