package io.spring.studycafe.presentation.authorization;

import io.spring.studycafe.authorization.AuthorizationManager;
import io.spring.studycafe.authorization.AuthorizationToken;
import io.spring.studycafe.authorization.jwt.exception.AuthorizationException;
import io.spring.studycafe.config.handler.exception.AuthorizationExceptionCode;
import io.spring.studycafe.config.handler.exception.TokenAuthorizationException;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/authorization")
@RestController
public class AuthorizationController {

    private final AuthorizationManager authorizationManager;

    public AuthorizationController(AuthorizationManager authorizationManager) {
        this.authorizationManager = authorizationManager;
    }

    @Hidden
    @PostMapping("/refresh")
    public ResponseEntity<AuthorizationToken> refreshToken(HttpServletRequest request) {
        String refreshToken = request.getHeader("REFRESH_TOKEN");
        try {
            AuthorizationToken authorizationToken = authorizationManager.validateRefreshToken(refreshToken);
            return ResponseEntity.ok(authorizationToken);
        } catch (AuthorizationException e) {
            throw new TokenAuthorizationException(AuthorizationExceptionCode.INVALID_TOKEN);
        }

    }
}
