package or.samir.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
//activer la security il faudrait redefinir deux  metode
public class SecurityConfig extends WebSecurityConfigurerAdapter {
@Autowired
private DataSource datasource;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		/*auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN","USER");
		auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");*/
		/*auth.jdbcAuthentication().dataSource(datasource)
		.usersByUsernameQuery("select username as principal, password as credentials, true from user where username=?")
		.authoritiesByUsernameQuery("select user_username as principal, roles_role as role from users_roles where user_username=?")
		.rolePrefix("ROLE_")*/
		//.passwordEncoder(new Md5PasswordEncoder());
		//super.configure(auth);
	}
@Override
//definir la stratégie de security
protected void configure(HttpSecurity http) throws Exception {
	// TODO Auto-generated method stub
	http.formLogin().loginPage("/login");
	http.authorizeRequests().antMatchers("/operations","/consultercompte").hasRole("USER");
	http.authorizeRequests().antMatchers("/saveOperation").hasRole("ADMIN");
	http.exceptionHandling().accessDeniedPage("/403");
	//super.configure(http);
}
}
