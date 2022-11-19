package com.zerobase.fastlms.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        log.warn("Login Success");

        List<String> roleNames = new ArrayList<>();
        authentication.getAuthorities().forEach(authority -> {
            roleNames.add(authority.getAuthority());
        });

        log.warn("ROLE NAMES : "+roleNames);
        if(roleNames.contains("ROLE_ADMIN")) {
            response.sendRedirect("/admin/main");
            return;
        }

        response.sendRedirect("/");
        super.onAuthenticationSuccess(request, response, chain, authentication);
    }
}
