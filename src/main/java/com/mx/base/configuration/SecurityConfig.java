package com.mx.base.configuration;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.mx.base.models.catalog.Profile;
import com.mx.base.repository.ProfileRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger LOGGER = LogManager.getLogger(SecurityConfig.class);

	private static final String ACTIVE_STATUS = "A";

	private static final String LOGIN_PATH = "/login";

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private DataSource dataSource;

	@Value("${spring.queries.users-query}")
	private String usersQuery;

	@Value("${spring.queries.roles-query}")
	private String rolesQuery;

	@Autowired
	@org.springframework.beans.factory.annotation.Qualifier("profileRepository")
	private ProfileRepository profileRepository;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().usersByUsernameQuery(usersQuery).authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/login").permitAll()
		.antMatchers("/home").authenticated()
		.antMatchers("/user**").hasRole("USER").anyRequest().authenticated()
		.antMatchers("/admin**").hasRole("ADMIN").anyRequest().authenticated()
		.antMatchers("/catalog/**").hasRole("ADMIN").anyRequest().authenticated()
		.and().csrf().and().formLogin().loginPage("/login")
				.failureUrl("/login?error=1").defaultSuccessUrl("/home", true)
				.usernameParameter("username")
				.passwordParameter("password")
				.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login?msg=1").and().exceptionHandling().accessDeniedPage("/login");

//		this.setRoles(
//				http.authorizeRequests().antMatchers("/").permitAll()
//				.antMatchers(LOGIN_PATH).permitAll()
//				.and().csrf().and().formLogin().and()).formLogin()
//				.loginPage(LOGIN_PATH).failureUrl(LOGIN_PATH+"?error=1").defaultSuccessUrl("/home", true)
//				.usernameParameter("username").passwordParameter("password").and()
//				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl(LOGIN_PATH+"?msg=1")
//				.and().exceptionHandling().accessDeniedPage(LOGIN_PATH);

	}

	private HttpSecurity setRoles(HttpSecurity http) {
		List<Profile> profiles = this.profileRepository.findByStatus(ACTIVE_STATUS);
		profiles.forEach(profile -> {
			profile.getTcProfileMenus().forEach(profileMenu -> {
				try {
					http.authorizeRequests().antMatchers(profileMenu.getTcMenu().getSpringAntPath())
							.hasRole(profile.getSpringRole()).anyRequest().authenticated();
				} catch (Exception e) {
					LOGGER.error(e.getMessage(), e);
					throw new RuntimeException(e);
				}
			});
		});
		return http;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

}
