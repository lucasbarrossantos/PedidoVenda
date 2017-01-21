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
                    .antMatchers("/index.xhtml", "/AcessoNegado.xhtml", "/dialogos/**").authenticated()
                    .antMatchers("/pedidos/**").hasAnyAuthority("VENDEDORES", "AUXILIARES", "ADMINISTRADORES")
                    .antMatchers("/produtos/**").hasAnyAuthority("ADMINISTRADORES")
                    .antMatchers("/clientes/**").hasAnyAuthority("ADMINISTRADORES", "VENDEDORES", "AUXILIARES")
                    .antMatchers("/relatorios/**").hasAnyAuthority("ADMINISTRADORES", "VENDEDORES")
                    .antMatchers("/usuarios/**").hasAnyAuthority("ADMINISTRADORES")
                    .and()

        .formLogin()
            .loginPage("/login.xhtml")
            .failureForwardUrl("/login.xhtml?invalid=true")
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
