package pl.coderslab.charity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import pl.coderslab.charity.service.MyUserDetailsService;


@Configuration
@EnableWebSecurity
//@Transactional
@ComponentScan("pl.coderslab.charity")
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final MyUserDetailsService myUserDetailsService;

    @Autowired
    public SecurityConfig(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);

        //add your custom encoding filter as the first filter in the chain
        http.addFilterBefore(new EncodingFilter(), ChannelProcessingFilter.class);

        http.authorizeRequests()
                        .antMatchers("/", "/contact", "/about", "/register", "/login").permitAll() //allow public access
                        .antMatchers("/app/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')") //strony, które potrzebują user, admin
                        .antMatchers("/resources/static/**").permitAll()
                        .antMatchers("/css/**", "/images/**", "/js/**").permitAll()
                        .anyRequest().authenticated()
                .and()
                .formLogin()
                        .loginPage("/login")
                        .loginProcessingUrl("/authenticateTheUser")
                        .defaultSuccessUrl("/");
//                        .permitAll();
                //tutaj można dodać log-out

    }

}
