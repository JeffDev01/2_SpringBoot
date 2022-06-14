package br.com.alura.mudi.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private DataSource dataSource; //realiza a conexão com DB

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/home/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/usuario/home", true)
                        .permitAll()
                )
                .logout(logout -> logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/home"))
                .csrf().disable(); //Prevenção de ataques Cross-site Request Forgery (CSRF). ESTUDAR!!!
    }


    //esse metodo indica que vamos trabalhar com jdbc para pegar usuario e senha
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); //faz a criptografia da senha

//        UserDetails user =
//                User.builder()
//                        .username("ana")
//                        .password(encoder.encode("ana"))
//                        .roles("ADM")
//                        .build();


        auth.jdbcAuthentication()
                .dataSource(dataSource) //faz a connexao com banco de dados
                .passwordEncoder(encoder); //faz a criptografia da senha
//                .withUser(user);
    }


}