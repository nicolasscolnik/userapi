// package com.example.userapi.service;

// import com.example.userapi.model.User;
// import com.example.userapi.repository.UserRepository;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.Optional;

// @Service
// public class UserService {

//     private final UserRepository userRepo;

//     public UserService(UserRepository userRepo) {
//         this.userRepo = userRepo;
//     }

//     public List<User> getAllUsers() {
//         return userRepo.findAll();
//     }

//     public Optional<User> getUserById(Long id) {
//         return userRepo.findById(id);
//     }

//     public User createUser(User user) {
//         return userRepo.save(user);
//     }

//     public void deleteUser(Long id) {
//         userRepo.deleteById(id);
//     }

//     public User updateUser(Long id, User updatedUser) {
//         return userRepo.findById(id)
//                 .map(user -> {
//                     user.setName(updatedUser.getName());
//                     user.setEmail(updatedUser.getEmail());
//                     return userRepo.save(user);
//                 })
//                 .orElse(null);
//     }
// }

package com.example.userapi.service;

import com.example.userapi.model.User;
import com.example.userapi.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public Page<User> getAllUsers(Pageable pageable) {
        return userRepo.findAll(pageable);
    }

    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id);
    }

    @Transactional
    public User createUser(User user) {
        if (userRepo.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        return userRepo.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    @Transactional
    public User updateUser(Long id, User updatedUser) {
        return userRepo.findById(id)
                .map(user -> {
                    if (!user.getEmail().equalsIgnoreCase(updatedUser.getEmail())
                            && userRepo.existsByEmail(updatedUser.getEmail())) {
                        throw new IllegalArgumentException("El email ya está registrado");
                    }
                    user.setName(updatedUser.getName());
                    user.setEmail(updatedUser.getEmail());
                    return userRepo.save(user);
                })
                .orElse(null);
    }
}
