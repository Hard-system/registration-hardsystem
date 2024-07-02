package business.interfaces;


import business.model.JwtAuthenticationResponse;
import business.model.SignUpRequest;
import business.model.SigninRequest;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}