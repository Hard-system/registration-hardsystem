package business.service;


import business.enums.Role;
import business.interfaces.AuthenticationService;
import business.interfaces.JwtService;
import business.model.User;
import business.repository.UserRepository;
import business.model.JwtAuthenticationResponse;
import business.model.SignUpRequest;
import business.model.SigninRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        validatePassword(request.getPassword());
        validateName(request.getFirstName(), "First name");
        validateName(request.getLastName(), "Last name");
        validateEmail(request.getEmail());

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .personRole(Role.USER)
                .build();

        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        return jwtAuthenticationResponse;
    }

    private void validatePassword(String password) {
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
        if (!password.matches(passwordRegex)) {
            throw new IllegalArgumentException("Password must be at least 8 characters long and contain at least 1 uppercase letter, 1 lowercase letter, and numbers.");
        }
    }

    private void validateName(String name, String fieldName) {
        String nameRegex = "^[a-zA-Z]+$";
        if (!name.matches(nameRegex)) {
            throw new IllegalArgumentException(fieldName + " must contain only letters.");
        }
    }

    private void validateEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!email.matches(emailRegex)) {
            throw new IllegalArgumentException("Invalid email format.");
        }
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        return jwtAuthenticationResponse;
    }
}