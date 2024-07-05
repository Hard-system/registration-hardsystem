package logic.controller;


import logic.interfaces.AuthenticationService;
import logic.model.JwtAuthenticationResponse;
import logic.model.SignUpRequest;
import logic.model.SigninRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @Async("asyncTaskExecutor")
    @PostMapping("/auth/signup")
    public CompletableFuture<ResponseEntity<JwtAuthenticationResponse>> signup(@RequestBody SignUpRequest request) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return ResponseEntity.ok(authenticationService.signup(request));
            } catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException("Invalid signup request: " + ex.getMessage());
            }
        });
    }

    @Async("asyncTaskExecutor")
    @PostMapping("/auth/signin")
    public CompletableFuture<ResponseEntity<JwtAuthenticationResponse>> signin(@RequestBody SigninRequest request) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return ResponseEntity.ok(authenticationService.signin(request));
            } catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException("Invalid signin request: " + ex.getMessage());
            }
        });
    }
}