package goal_maker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import goal_maker.web.services.user_service.UserLoginDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
		
	@Autowired
	@Qualifier("userDetailsService")
	UserDetailsService userDetailsService;
	    
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
    	http
		.authorizeRequests()
		.anyRequest().authenticated()
		.and()
			.formLogin()
			.loginPage( "/login" ).permitAll()
		.and()
			.rememberMe().tokenValiditySeconds( 60*60*24*31 ).rememberMeParameter( "remember-me" ).key( "uniqueAndSecret" )
		.and().logout().logoutUrl( "/logout" ).logoutSuccessUrl( "/login" );

    }
    
    @Override
	public void configure( WebSecurity web ) throws Exception{
		web.ignoring().antMatchers( "/webjars/**" );
	}
    
    @Override
	protected void configure( AuthenticationManagerBuilder auth ) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());	
	}
    
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

}
