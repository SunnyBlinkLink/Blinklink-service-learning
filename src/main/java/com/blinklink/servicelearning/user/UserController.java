package com.blinklink.servicelearning.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController{

    private final UserService userService;
    @PostMapping(value="/users")
    public ResponseEntity<?> create(@RequestBody CreateResource createResource){
        var user=new User(createResource.getName(), createResource.getEmail(), createResource.getMobile());
        user=userService.create(user);
        return ResponseEntity.ok(user);
    }

    @Getter
    private static class CreateResource{
        private final String name;
        private final String email;
        private final String mobile;

        @JsonCreator
        public CreateResource(String name, String email,String mobile){
            this.name=name;
            this.email=email;
            this.mobile=mobile;
        }

    }

    @GetMapping(value = "/users/{name}")
    public ResponseEntity<?> getUser(@PathVariable("name") String name){
        var user=userService.find(name);
        return ResponseEntity.ok(user);
    }
}

