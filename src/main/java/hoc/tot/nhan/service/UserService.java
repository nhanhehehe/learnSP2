package hoc.tot.nhan.service;

import hoc.tot.nhan.dto.request.UserCreationRequest;
import hoc.tot.nhan.dto.request.UserUpdateRequest;
import hoc.tot.nhan.entity.User;
import hoc.tot.nhan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreationRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setDob(request.getDob());
        return userRepository.save(user);
    }


    public User updateUser(String id, UserUpdateRequest request) {
        User user = findById(id);

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setDob(request.getDob());
        return userRepository.save(user);
    }

    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void deleteUserById(String id) {
        User user = findById(id);
        userRepository.delete(user);
    }

    public User getUserById(String id) {
       return findById(id);
    }

    public List<User> getAllUsers() {
        return findAll();
    }
}
