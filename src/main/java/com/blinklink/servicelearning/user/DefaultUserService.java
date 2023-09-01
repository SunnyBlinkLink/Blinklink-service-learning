package com.blinklink.servicelearning.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService{

    private final UserRepository userRepository;

    @Override
    public User create(User user) {
        var existingUser= userRepository.findByName(user.getName()).orElse(null);
        if(existingUser!=null){
            return existingUser;
        }

        return userRepository.save(user);
    }

    @Override
    public User find(String name) {
        return userRepository.findByName(name).orElseThrow(()->new RuntimeException("user not found"));
    }

}
