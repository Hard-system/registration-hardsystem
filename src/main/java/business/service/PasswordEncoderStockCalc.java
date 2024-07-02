package business.service;

import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoderStockCalc {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public PasswordEncoderStockCalc() {
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public String encode(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }
}
