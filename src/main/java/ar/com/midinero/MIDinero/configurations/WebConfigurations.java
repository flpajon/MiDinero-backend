package ar.com.midinero.MIDinero.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import ar.com.midinero.MIDinero.jwt.JwtEntryPoint;
import ar.com.midinero.MIDinero.jwt.JwtTokenFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebConfigurations extends WebSecurityConfigurerAdapter {

	private static final String rolAdmin = "ROLE_ADMIN";
	private static final String rolNormal = "ROLE_NORMAL";
	
	private static final String[] endpoints_ADMIN = new String[] { "/v1/user/*" };
	private static final String[] endpoints_NORMAL = new String[] {"/v1/movement-type/*", "/v1/movement/*"};
	private static final String[] endpoints_PUBLIC = new String[] {"/v1/auth/*","/swagger-ui/*", "/v3/api-docs/*", "/v3/api-docs"};

	@Autowired
	JwtEntryPoint jwtEntryPoint;

	@Bean
	public JwtTokenFilter jwtTokenFilter() {
		return new JwtTokenFilter();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests().antMatchers(endpoints_PUBLIC).permitAll()
				.antMatchers(endpoints_ADMIN).hasAuthority(rolAdmin).antMatchers(endpoints_NORMAL)
				.hasAuthority(rolNormal).anyRequest().authenticated().and().exceptionHandling()
				.authenticationEntryPoint(jwtEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}

}
