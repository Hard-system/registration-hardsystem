package logic.service;

import logic.model.User;
import logic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public void register(User user) {
        userRepository.save(user);
    }

    public User login(String username, String password) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));

        return user;
    }

    public void forgotPassword(String email) throws RuntimeException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            //Here I need to add the logic for forgotten password.

        } else {
            throw new RuntimeException("User with this email does not exist!");
        }
    }
}
