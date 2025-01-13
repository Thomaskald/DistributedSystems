package gr.hua.dit.Adoption.handlers;

import gr.hua.dit.Adoption.exceptions.UserNotEnabledException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        if (exception.getCause() instanceof UserNotEnabledException) {
            // Redirect with a custom error message
            request.getSession().setAttribute("error", exception.getMessage());
        } else {
            // Default behavior for invalid credentials
            request.getSession().setAttribute("error", "Invalid username or password");
        }
        response.sendRedirect("/login");
    }
}
