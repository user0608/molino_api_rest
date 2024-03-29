package com.saucedo.molino;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.saucedo.molino.security.filters.JwtAuthorizationFilter;
import com.saucedo.molino.security.services.UsuarioDetailsService;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
		
	    @Autowired
	    private UsuarioDetailsService userDetailsService;

	  

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	    	http.csrf().disable().httpBasic()
	    	.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	    	.and().addFilter(jwtAuthorizationFilter());
	    }
	    @Bean
	    public AuthenticationManager customAuthenticationManager() throws Exception {
	        return authenticationManager();
	    }

	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	    }
	    @Bean
	    public BCryptPasswordEncoder bCryptPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	    
	    @Bean
	    public JwtAuthorizationFilter jwtAuthorizationFilter() throws Exception {
	    	return new JwtAuthorizationFilter(this.authenticationManager());
	    }
}
