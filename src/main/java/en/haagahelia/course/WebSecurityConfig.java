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
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http 
            .authorizeRequests().antMatchers("/css/**").permitAll()
	        .and()
	        .authorizeRequests().antMatchers("/signup", "/saveuser").permitAll()
	        .and()
            .authorizeRequests().anyRequest().authenticated()
            .and()
        .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/booklist")
            .permitAll()
            .and()
        .logout()
            .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	
        /*auth
        .inMemoryAuthentication()
            .withUser("user").password("password").roles("USER");
	    auth
	    .inMemoryAuthentication()
	    		.withUser("admin").password("password").roles("ADMIN");*/
    	
    	auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}