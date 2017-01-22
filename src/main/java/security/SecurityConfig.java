package security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public AppUserDetailsService userDetailsService() {
        return new AppUserDetailsService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JsfLoginUrlAuthenticationEntryPoint jsfLoginEntry = new JsfLoginUrlAuthenticationEntryPoint();
        jsfLoginEntry.setLoginFormUrl("/login.xhtml");
        jsfLoginEntry.setRedirectStrategy(new JsfRedirectStrategy());

        JsfAccessDeniedHandler jsfDeniedEntry = new JsfAccessDeniedHandler();
        jsfDeniedEntry.setLoginPath("/AcessoNegado.xhtml");
        jsfDeniedEntry.setContextRelative(true);

        http
                .csrf().disable()
                .headers().frameOptions().sameOrigin()
                .and()

          .authorizeRequests()
                    .antMatchers("/login.xhtml", "/Erro.xhtml", "/javax.faces.resource/**").permitAll()
                    .antMatchers("/index.xhtml", "/AcessoNegado.xhtml", "/dialogos/**").authenticated() // Precisa estar autenticado
                    .antMatchers("/pedidos/**").hasAnyRole("VENDEDORES", "AUXILIARES", "ADMINISTRADORES") // Precisa ter permissão de...
                    .antMatchers("/produtos/**").hasAnyRole("ADMINISTRADORES")
                    .antMatchers("/clientes/**").hasAnyRole("ADMINISTRADORES", "VENDEDORES", "AUXILIARES")
                    .antMatchers("/relatorios/**").hasAnyRole("ADMINISTRADORES", "VENDEDORES")
                    .antMatchers("/usuarios/**").hasAnyRole("ADMINISTRADORES")
                    .and()

        .formLogin()
            .loginPage("/login.xhtml")
            .failureUrl("/login.xhtml?invalid=true")
            .and()

        .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .and()

        .exceptionHandling()
            .accessDeniedPage("/AcessoNegado.xhtml")
            .authenticationEntryPoint(jsfLoginEntry)
            .accessDeniedHandler(jsfDeniedEntry);
    }
}
