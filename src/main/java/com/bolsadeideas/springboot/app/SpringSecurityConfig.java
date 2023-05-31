package com.bolsadeideas.springboot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.bolsadeideas.springboot.app.auth.handler.LoginSuccessHandler;
import com.bolsadeideas.springboot.app.models.service.JpaUserDetailsService;

@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
public class SpringSecurityConfig {
	@Autowired
	private LoginSuccessHandler successHandler;
	
	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
    @Bean
    public static BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //Metodo para configurar roles y contraseña para ingresar
    /*@Bean
    public UserDetailsService userDetailsService() throws Exception{
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
 
        manager.createUser(User.withUsername("hector")
                .password(passwordEncoder().encode("123"))
                .roles("USER").build());
        manager.createUser(User.withUsername("admin")
                .password(passwordEncoder().encode("123"))
                .roles("ADMIN","USER").build());
        return manager;
 
    }*/
    //authorization en las rutas

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/","/css/**","/js/**","/images/**","/listar**", "/locale", "/api/clientes/**").permitAll()
                /*.requestMatchers("/form/**", "/eliminar/**", "/factura/**").hasRole("ADMIN")
                .requestMatchers("/ver/**","/uploads").hasRole("USER")*/
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().successHandler(successHandler).loginPage("/login").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/error_403");
        return http.build();
 
 
    }
 
	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception{
		
		build.userDetailsService(userDetailsService)
		//.dataSource(dataSource)
		.passwordEncoder(passwordEncoder)
		/*.usersByUsernameQuery("select username, password, enabled from users where username = ?")
		.authoritiesByUsernameQuery("select u.username, a.authority from authorities a inner join users u on (a.user_id=u.id) where u.username = ?")*/;
	
	}
 
 
}

/*
@Configuration
public class SpringSecurityConfig {
    
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
	}
	
    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public UserDetailsService userDetailsService()throws Exception{
                
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User
                .withUsername("hector")
                .password(passwordEncoder().encode("123"))
                .roles("USER")
                .build());
         manager.createUser(User
                    .withUsername("admin")
                    .password(passwordEncoder().encode("123"))
                    .roles("ADMIN","USER")
                    .build());
        
        return manager;
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> {
                try {
                    authz
                    .requestMatchers("/", "/css/**", "/js/**", "/images/**", "/client-list").permitAll()
                    .requestMatchers("/uploads/**").hasAnyRole("USER")
                    .requestMatchers("/client-show/**").hasRole("USER")
                    .requestMatchers("/invoice/**").hasRole("ADMIN")
                        .requestMatchers("/client-form/**").hasRole("ADMIN")
                        .anyRequest().authenticated().and().formLogin().permitAll().and().logout().permitAll();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
                
            );
        return http.build();
        
    }
     
    /*@Configuration
	 class SpringSecurityConfig extends WebSecurityConfiguration{
	
		@Bean
		public BCryptPasswordEncoder passeordEncoder()
		{
			return new BCryptPasswordEncoder();
		}
		
		
		@Autowired
		public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception{
			
			PasswordEncoder encoder = passeordEncoder();
			UserBuilder users = User.builder().passwordEncoder(password -> encoder.encode(password));
			
			builder.inMemoryAuthentication()
			.withUser(users.username("admin").password("123").roles("ADMIN", "USER"))
			.withUser(users.username("hector").password("123").roles("USER"));
		
	}
}
}*/
