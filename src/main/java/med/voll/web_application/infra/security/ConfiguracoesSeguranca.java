package med.voll.web_application.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class ConfiguracoesSeguranca {

//    @Bean
//    public UserDetailsService dadosUsuariosCadastrados() {
//        UserDetails usuario1 = User.builder()
//                .username("joao@email.com")
//                .password("{noop}joao123")
//                .build();
//
//        UserDetails usuario2 = User.builder()
//                .username("maria@email.com")
//                .password("{noop}maria123")
//                .build();
//
//        return new InMemoryUserDetailsManager(usuario1, usuario2);
//    }

    @Bean
    public PasswordEncoder codificadorSenha() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filtrosSeguranca(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(req -> {
                    req.requestMatchers("/css/**", "/js/**", "/assets/**",
                                    "/", "/index", "/home", "/esqueci-minha-senha", "recuperar-conta").permitAll();
//                    req.requestMatchers("/pacientes/**").hasRole("ATENDENTE");
//                    req.requestMatchers(HttpMethod.GET, "/medicos").hasAnyRole("ATENDENTE", "PACIENTE");
//                    req.requestMatchers("/medicos/**").hasRole("ATENDENTE");
//                    req.requestMatchers(HttpMethod.POST, "/consultas/**").hasAnyRole("ATENDENTE", "PACIENTE");
//                    req.requestMatchers(HttpMethod.PUT, "/consultas/**").hasAnyRole("ATENDENTE", "PACIENTE");
                    req.anyRequest().authenticated();
                })
                .formLogin(form -> form.loginPage("/login")
                    .defaultSuccessUrl("/").permitAll())
                    .logout(logout -> logout.logoutSuccessUrl("/login?logout")
                    .permitAll()).rememberMe( rememberMe -> rememberMe.key("lembrarDeMim")
                        .alwaysRemember(true))
                .csrf(Customizer.withDefaults())
                        //.tokenValiditySeconds()
                .build();
    }
}
