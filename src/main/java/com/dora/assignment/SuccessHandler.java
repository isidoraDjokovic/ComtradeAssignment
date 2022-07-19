package com.dora.assignment;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class SuccessHandler implements AuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException {
    String redirectUrl = "";

    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    for (GrantedAuthority grantedAuthority : authorities) {
      if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
        redirectUrl = "/user";
        break;
      } else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
        redirectUrl = "/admin";
        break;
      }
    }
    new DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl);
  }
}
