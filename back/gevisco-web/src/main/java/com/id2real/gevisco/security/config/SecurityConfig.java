/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id2real.gevisco.security.config;

//import com.cpc.cfinder.security.CustomLogoutHandler;
//import com.cpc.core.sec.security.SecurityConfiguration;
//import com.cpc.core.security.jwt.JwtAuthenticationTokenFilter;
//import com.cpc.core.utils.audit.UserAgentInfoFilter;
//import com.ngs.abs.config.filters.ContextFilter;
//import com.ngs.abs.bud.nomenclature.utils.BudRoleConstant;
//import com.ngs.core.codification.services.GenericSellingParamsFilter;
//import com.ngs.core.codification.utils.SprRoleConstantes;
//import com.ngs.core.utils.audit.UserAgentInfoFilter;
//import com.ngs.nextframe.account.filter.ForcerChangementPwdFilter;
//import com.ngs.nextframe.account.utils.SecRoleConstantes;
//import com.ngs.nextframsp.core.security.jwt.JwtAuthenticationTokenFilter;
import com.id2real.socle.security.config.CustomLogoutHandler;
import com.id2real.socle.security.config.SecurityConfiguration;
import com.id2real.socle.security.config.SimpleCorsFilter;
import com.id2real.socle.security.jwt.JwtAuthenticationTokenFilter;
import com.id2real.socle.utils.audit.UserAgentInfoFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author WKOUWONOU
 * @since 09/03/2018 configuration du contexte de securité
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends SecurityConfiguration {

    @Autowired
    private CustomLogoutHandler logoutHandler;
//    @Autowired
//    private ContextFilter contextFilter;

    @Autowired
    private UserAgentInfoFilter userAgentInfoFilter;
    @Autowired
    private JwtAuthenticationTokenFilter authenticationTokenFilter;
//    @Autowired
//    private GenericSellingParamsFilter genericSellingParamsFilter;
//    @Autowired
//    private ForcerChangementPwdFilter changementPwdFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .headers().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/javax/**", "/js/**", "/plugins/**", "/css/**").permitAll()
                .antMatchers("/sec/js/**").permitAll()
                .antMatchers("/resetpassword").permitAll()
                .antMatchers("/api/import/**").permitAll()
                .antMatchers("/api/np/sendReinitMail/mail/**").permitAll()
                .antMatchers("/api/np/sendReinitMail/mail").permitAll()
                .antMatchers("/resetpassword/reinitMail").permitAll()
                .antMatchers("/resetpassword/**").permitAll()
                .antMatchers("/menu/app-logo").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/errors/conf/selling").permitAll()
                /**
                 * Socle url security
                 */
                //                .antMatchers(SprRoleConstantes.PARAMETRES_URL).hasAnyAuthority(SprRoleConstantes.FC_CONFIGURATION_BASE, SprRoleConstantes.ADMIN)
                //                .antMatchers(SprRoleConstantes.LISTE_URL).hasAnyAuthority(SprRoleConstantes.FC_CONFIGURATION_BASE, SprRoleConstantes.ADMIN)
                //                .antMatchers(SprRoleConstantes.LISTE_PATH).hasAnyAuthority(SprRoleConstantes.FC_CONFIGURATION_BASE, SprRoleConstantes.ADMIN)
                //                .antMatchers(SprRoleConstantes.EXTENSION_URL).hasAnyAuthority(SprRoleConstantes.EXTENSION, SprRoleConstantes.ADMIN)
                //                .antMatchers(SprRoleConstantes.ETIQUETTE_URL).hasAnyAuthority(SprRoleConstantes.FC_CONFIGURATION_BASE, SprRoleConstantes.ADMIN)
                //                .antMatchers(SprRoleConstantes.WORKFLOW_URL).hasAnyAuthority(SprRoleConstantes.WORKFLOW, SprRoleConstantes.ADMIN)
                //                .antMatchers(SprRoleConstantes.PROPRIETE_ETENDUE_URL).hasAnyAuthority(SprRoleConstantes.EXTENSION, SprRoleConstantes.ADMIN)
                //                .antMatchers(SprRoleConstantes.STRUCTURE_DE_CONTROLE_URL).hasAnyAuthority(SprRoleConstantes.STRUCTURE_DE_CONTROLE, SprRoleConstantes.ADMIN)
                /**
                 * SEC
                 */
                //                .antMatchers(SecRoleConstantes.UTILISATEUR_URL).hasAnyAuthority(SecRoleConstantes.UTILISATEUR, SprRoleConstantes.ADMIN)
                //                .antMatchers(SecRoleConstantes.SITE_URL).hasAnyAuthority(SecRoleConstantes.SITE, SprRoleConstantes.ADMIN)
                //                .antMatchers(SecRoleConstantes.ENTITE_URL).hasAnyAuthority(SecRoleConstantes.ENTITE, SprRoleConstantes.ADMIN)
                //                .antMatchers(SecRoleConstantes.PROFIL_URL).hasAnyAuthority(SecRoleConstantes.PROFIL, SprRoleConstantes.ADMIN)

                /**
                 * BUD
                 */
                //                .antMatchers(BudRoleConstant.FC_INDICATEUR_URL, BudRoleConstant.FC_INDICATEUR_URL_API).hasAnyAuthority(BudRoleConstant.FC_INDICATEUR_LIRE, SprRoleConstantes.ADMIN)
                //                .antMatchers(BudRoleConstant.FC_NOMENCLATURE_URL, BudRoleConstant.FC_NOMENCLATURE_URL_API).hasAnyAuthority(BudRoleConstant.FC_NOMENCLATURE_LIRE, SprRoleConstantes.ADMIN)
                //                .antMatchers(BudRoleConstant.FC_ENCODATE_BUDGETAIRE_URL, BudRoleConstant.FC_ENCODATE_BUDGETAIRE_URL_API).hasAnyAuthority(BudRoleConstant.FC_ENCODATE_BUDGETAIRE_LIRE, SprRoleConstantes.ADMIN)
                //                .antMatchers("/personne").authenticated()
//                .antMatchers("/api/security/auth/signup").permitAll()
                
                .antMatchers("/api/security/auth/login").permitAll()
                .antMatchers("/api/security/auth/logout").permitAll()
                .antMatchers("/api/security/auth/token/refresh").permitAll()
////                .antMatchers("/api/security/auth/info").permitAll()
                
                .antMatchers("/api/security/user/sendActivationMail/**").permitAll()
                .antMatchers("/api/security/user/sendPasswordReset/**").permitAll()
                .antMatchers("/api/security/user/resetPassword/**").permitAll()
                
                // TEST
                
                .antMatchers("/api/security/user/save").permitAll()
                .antMatchers("/api/tiers/pp/register/save").permitAll()
                .antMatchers("/api/tiers/referentiel/register/find/**").permitAll()
                .antMatchers("/api/tiers/profession/register/all").permitAll()
                .antMatchers("/api/security/profil/register/find/**").permitAll()
                
                
                //Gevisco
                .antMatchers("/gevisco/**").permitAll()
                .antMatchers("/api/preinscripion/an").permitAll()
                
                //                .antMatchers("/api/personne/register").permitAll()
                //                .antMatchers("/api/personne/count").permitAll()
                //                .antMatchers("/api/personne/activate/**").permitAll()
                //                
                //                .antMatchers("/api/personne/cloudstack/**").permitAll()
                //                .antMatchers("/console").permitAll()
                //                
                //                
                //                .antMatchers("/api/langue/all").permitAll()
                //                .antMatchers("/api/denomination/all").permitAll()
                //                .antMatchers("/api/entite/get/**").permitAll()
                //                .antMatchers("/api/entite/count").permitAll()
                //                .antMatchers("/api/entite/search").permitAll()

                //                .antMatchers("api/picture/changeUserPicture/**").authenticated()
                //                .antMatchers("api/picture/uploadFile/**").authenticated()
                //                .antMatchers("api/codifList/goToNextStep/**").authenticated()
                //                .antMatchers("api/picture/viewFile/user/**").authenticated()
                //                .antMatchers("api/ged/codifList/**").authenticated()
                .anyRequest().authenticated()
                .and()
                .addFilterAfter(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                /* LICENCE CONTROLE*/
                //                .addFilterBefore(genericSellingParamsFilter, BasicAuthenticationFilter.class)
                //                .addFilterAfter(contextFilter, BasicAuthenticationFilter.class)
                //                .addFilterAfter(changementPwdFilter, ContextFilter.class)
                //                .addFilterBefore(userAgentInfoFilter, ContextFilter.class)
                .exceptionHandling()
                .accessDeniedHandler(getRestAccessDeniedHandler())
                .defaultAuthenticationEntryPointFor(
                        getUnauthorizedEntryPoint(),
                        new AntPathRequestMatcher("/api/**"))
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(getRestAuthenticationSuccessHandler())
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll().and()
                .logout()
                .addLogoutHandler(logoutHandler)
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll()
                .permitAll().and().httpBasic();

    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new SimpleCorsFilter());
        registrationBean.setName("CORS FIlter");
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
