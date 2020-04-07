package com.explore.canada.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

/*
 * This code comes from this tutorial:
 * https://dzone.com/articles/add-login-to-your-spring-boot-app-in-10-mins
 * 
 * I'm going to make it so that every page on the site requires authentication. I will use the Spring
 * security mechanisms to enforce this. If a user is not authenticated this is the class that will
 * redirect them somewhere to login/sign up.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
	@Override
   public void configure(WebSecurity web) throws Exception
	{
   		web.ignoring().antMatchers("/resources/**");
    }

	@Autowired
	private AccessDeniedHandler accessDeniedHandler;

	// roles admin allow to access /admin/**
	// roles user allow to access /user/**
	// custom 403 access denied handler
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable()
				.authorizeRequests()
				.antMatchers("/", "/**").permitAll()
				.antMatchers("/home/**").permitAll()
				.antMatchers("/static/**").permitAll()
				.antMatchers("/public/**", "/**").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/user/**").hasRole("USER")
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/onetimepassword",true)
				.failureUrl("/login?error=true")
				.permitAll()
				.and()
				.logout()
				.logoutSuccessUrl("/login")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.permitAll()
				.and()
				.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
	}

	@Autowired
	@Override
	protected AuthenticationManager authenticationManager() throws Exception
	{	
		return new CustomAuthenticationManager();
	}
}
