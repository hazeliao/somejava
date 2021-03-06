package com.sp17;

import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
    DataSource datasource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests().antMatchers("/css/**","/js/**","/images/**").permitAll()
		.and()
		.authorizeRequests().antMatchers("/form/{id}", "/formSubmission","/confirmation", "/industrypage").permitAll()
		.and()
		.authorizeRequests().anyRequest().authenticated()
		.and()		
	.formLogin()
		.loginPage("/login")
		.defaultSuccessUrl("/createForm")
		.permitAll()
		.and()
	.logout()
		.permitAll()
		.and()
	.exceptionHandling().accessDeniedPage("/403")
		.and()
	.csrf();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {		
		auth.jdbcAuthentication().dataSource(datasource)
		.usersByUsernameQuery("select username,password, enabled from Users where username=?")
		.authoritiesByUsernameQuery("select username, role from UserRoles where username=?");
	}
}
