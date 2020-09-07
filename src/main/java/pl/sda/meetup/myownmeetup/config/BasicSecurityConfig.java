package pl.sda.meetup.myownmeetup.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/restricted").authenticated()
                .antMatchers("/event").authenticated()
                .anyRequest()
                .permitAll()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .formLogin()
                .loginPage("/sign-in.html")
                .usernameParameter("email")
                .passwordParameter("password")
                .failureUrl("/sign-in?error=true")
                .defaultSuccessUrl("/homePage.html")
                .and()
                .logout()
                .logoutSuccessUrl("/homePage.html");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)//data source to interfejs z jdbc, jpa z niego też korzysta ale pod spodem, możemy go wyciągnąć tworząc z niego beana//dzięki temu wskazujemy z jakiej bazy ma pobierać dane do autentykacji
                .usersByUsernameQuery("SELECT u.email, u.password, 1 FROM user u WHERE u.email = ?")//tam powyżej te stringi mają odpowiadać nazwą kolumn w bazie danych//ta jedynka to aktywność naszego użytkownika//wpisujemy tam na sztywno że każdy jest aktywny bo nie mamy jeszcze określonej aktywności
                .authoritiesByUsernameQuery("SELECT u.email, r.role_name FROM user u JOIN user_role ur ON ur.user_id = u.id JOIN role r ON r.id = ur.role_id WHERE u.email = ?")
                .passwordEncoder(passwordEncoder);
    }
}
