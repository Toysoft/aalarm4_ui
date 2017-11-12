package net.kprod.aalarmui.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private Logger LOG = LoggerFactory.getLogger(SecurityConfig.class);

	@Value("${auth.enabled}")
	private boolean authEnabled;

	@Value("${auth.user.login}")
	private String adminLogin;

	@Value("${auth.user.pwd}")
	private String adminPwd;

	private static final String ROLE_ADMIN = "ADMIN";
	private static final String ROLE_ALL = "ALL";

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.passwordEncoder(passwordEncoder())
				.withUser(adminLogin).password(adminPwd).roles(ROLE_ADMIN);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if(!authEnabled) {
			http.authorizeRequests().anyRequest().permitAll().and().csrf().disable();
		} else {

			http
				.authorizeRequests()
				.anyRequest().hasAnyRole(ROLE_ADMIN)
				.and().httpBasic()
				.and().csrf().disable();
		}
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}