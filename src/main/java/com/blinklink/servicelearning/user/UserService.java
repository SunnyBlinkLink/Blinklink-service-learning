package com.blinklink.servicelearning.user;

public interface UserService {
    User create(User user);
    User find(String name);
}

