package com.example.writemymail.service.user;

import com.example.writemymail.domain.dto.UserRequest;
import com.example.writemymail.domain.entity.User;
import com.example.writemymail.error.CredentialsAlreadyExistsException;
import com.example.writemymail.error.UserNotFoundException;
import com.example.writemymail.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User createUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new CredentialsAlreadyExistsException("Пользователь с таким email уже существует");
        }
        return save(user);
    }

    @Override
    public void updateUser(UserRequest userRequest) {
        UUID userId = userRequest.getId();
        User user = findUserById(userId);
        user.setName(userRequest.getName());
        user.setInfo(userRequest.getInfo());
        save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    public User findUserById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + username));
    }

}
