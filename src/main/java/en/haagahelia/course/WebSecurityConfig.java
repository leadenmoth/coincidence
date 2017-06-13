package en.haagahelia.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import en.haagahelia.course.web.UserDetailServiceImpl;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
    private UserDetailServiceImpl userDetailsService;
	//Set up access rights
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http //non-authenticated users can access css folder, index and root redirect to index, signup and signup's save endpoint
            .authorizeRequests()
            	.antMatchers("/css/**", "/js/**", "/img/**", "/vendor/**", "/signup", "/saveuser", "/index", "/").permitAll() 
	        	.antMatchers("/delete/**", "/u/**").hasAuthority("ADMIN")
	        	.anyRequest().authenticated()
            .and()
	        .formLogin() //login page is /login, it can be accessed by unauthenticated users and after log-in they are redirected to statlist
	            .loginPage("/login")
	            .defaultSuccessUrl("/polllist")
	            .permitAll()
	            .and()
	        .logout()
	            .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	//Use BCrypt to handle authentication so that the passwords are hashed all the way    	
    	auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}