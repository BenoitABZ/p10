package com.library.LibraryRestApi.configuration;

import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
		
	
	
	 @Override
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 
	       
	        
		    auth.authenticationProvider(getProvider());
		        
	        auth.inMemoryAuthentication()
	            .withUser("utilisateur")
	            .password(passwordEncoder.encode("mdp"))
	            .authorities("USER");
	        
	           
	            
	           
}
		 
	  @Bean
	    public AuthenticationProvider getProvider() {
	        AppAuthProvider provider = new AppAuthProvider();
	        provider.setUserDetailsService(userDetailsService);
	        return provider;
	    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    http.httpBasic()
    .and()
        .authorizeRequests()     
        .antMatchers("/Connexion").permitAll()
        .antMatchers("/Bibliotheques").permitAll()
        .antMatchers("/Search/Emprunts/**").permitAll()
        .antMatchers("/Search/Ouvrages").permitAll()
        .antMatchers("/h2-console/**").permitAll()
        .antMatchers("/Liste-emprunts").authenticated()  
        .anyRequest().permitAll()  
    .and()
        .formLogin()
        .loginProcessingUrl("/login")
        .successHandler(new AuthentificationLoginSuccessHandler())
        .failureHandler(new SimpleUrlAuthenticationFailureHandler())
        .permitAll()
    .and()
        .logout()
        .logoutUrl("/logout")
        .logoutSuccessHandler(new AuthentificationLogoutSuccessHandler())
        .invalidateHttpSession(true)
        .permitAll();
         

http.csrf().disable().exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint());
http.headers().frameOptions().disable();
}
    
    
    private class AuthentificationLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request,
                                            HttpServletResponse response, Authentication authentication)
                throws IOException, ServletException {
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
    private class AuthentificationLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
        @Override
        public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                    Authentication authentication) throws IOException, ServletException {
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
    
    
    
    
    
   
    
 

}
