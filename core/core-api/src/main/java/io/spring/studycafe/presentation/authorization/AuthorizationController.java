package io.spring.studycafe.presentation.authorization;

import io.spring.studycafe.authorization.AuthorizationManager;
import io.spring.studycafe.authorization.AuthorizationToken;
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

    @PostMapping("/refresh")
    public ResponseEntity<AuthorizationToken> refreshToken(HttpServletRequest request) {
        String refreshToken = request.getHeader("REFRESH_TOKEN");

        AuthorizationToken authorizationToken = authorizationManager.validateRefreshToken(refreshToken);

        return ResponseEntity.ok(authorizationToken);
    }
}
