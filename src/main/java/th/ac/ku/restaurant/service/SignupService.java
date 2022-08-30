package th.ac.ku.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import th.ac.ku.restaurant.model.User;
import th.ac.ku.restaurant.repository.UserRepository;

@Service
public class SignupService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder; // ทำหน้าที่ encode password

    public boolean isUsernameAvailable(String username) {
        return repository.findByUsername(username) == null;
    }
    public void createUser(User user) {
        User record = new User();
        // copy เพื่อความปลอดภัย DTO Data Transfer Object
        record.setFirstName(user.getFirstName());
        record.setLastName(user.getLastName());
        record.setRole(user.getRole());
        record.setUsername(user.getUsername());
        String hashedPassword = passwordEncoder.encode(user.getPassword()); // hash password
        record.setPassword(hashedPassword);
        repository.save(record);
    }
    public User getUser(String username) {
        return repository.findByUsername(username);
    }
}
