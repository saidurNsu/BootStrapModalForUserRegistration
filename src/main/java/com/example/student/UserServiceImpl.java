package com.example.student;
import com.example.student.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(UserModel userModel) {
        User user = new User();
        user.setId(userModel.getId());
        user.setUsername(userModel.getUsername());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        user.setRole("USER");

        userRepository.save(user);

        return user;
    }

    @Override
    public void saveVerificationTokenForUser(String token, User user) {
        VerificationToken verificationToken= new VerificationToken(token,user);
        verificationTokenRepository.save(verificationToken);
    }
}
