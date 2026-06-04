@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/api/auth/**").permitAll()
            .antMatchers("/api/users/**").hasRole("ADMIN")
            .antMatchers("/api/invoices/**").hasAnyRole("ADMIN","ACCOUNTANT")
            .anyRequest().authenticated();
    }
}
