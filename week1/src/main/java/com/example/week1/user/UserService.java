package com.example.week1.user;

import com.example.week1.user.exception.LoginFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTokenManager tokenManager;

    public String login(String username, String password) {
        Optional<User> matchedUser = userRepository.findByUsername(username);
        if(matchedUser.isPresent()) {
            if(matchedUser.get().getPassword().equals(password)) {
                return tokenManager.generateTokenFromUsername(username);
            }
        }
        throw new LoginFailedException(username);
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getUsernameFromToken(String token) {
        return tokenManager.decodeTokenToUsername(token);
    }

    public void register(String username, String password) {
        User user = new User(username, password);
        userRepository.save(user);
    }

    public void setTokenManager(UserTokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }
}
