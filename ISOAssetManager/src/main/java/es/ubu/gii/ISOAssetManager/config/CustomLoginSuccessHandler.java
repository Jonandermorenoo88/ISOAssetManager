package es.ubu.gii.ISOAssetManager.config;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        boolean isAdmin = authorities.stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

        boolean isAuditor = authorities.stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_AUDITOR"));

        if (isAdmin) {
            response.sendRedirect("/panel");
        } else if (isAuditor) {
            response.sendRedirect("/empresas");
        } else {
            response.sendRedirect("/esperarol");
        }
    }
}
