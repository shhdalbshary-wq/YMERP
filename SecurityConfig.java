@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            // السماح بالدخول للـ Auth بدون تسجيل
            .antMatchers("/api/auth/**").permitAll()
            // صلاحيات المدير
            .antMatchers("/api/users/**").hasRole("ADMIN")
            // صلاحيات المحاسب
            .antMatchers("/api/invoices/**").hasAnyRole("ADMIN","ACCOUNTANT")
            .antMatchers("/api/reports/**").hasAnyRole("ADMIN","ACCOUNTANT")
            // صلاحيات المستخدم العادي
            .antMatchers("/api/profile/**").hasRole("USER")
            // أي طلب آخر يحتاج تسجيل دخول
            .anyRequest().authenticated()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
