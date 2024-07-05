package logic.interfaces;


import logic.model.JwtAuthenticationResponse;
import logic.model.SignUpRequest;
import logic.model.SigninRequest;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}