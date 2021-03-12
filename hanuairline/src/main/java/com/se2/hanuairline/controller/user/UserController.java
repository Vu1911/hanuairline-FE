package com.se2.hanuairline.controller.user;

import com.se2.hanuairline.exception.ResourceNotFoundException;
import com.se2.hanuairline.model.user.User;
import com.se2.hanuairline.repository.user.UserRepository;
import com.se2.hanuairline.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public User getUserProfile(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        return user;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllUsers (@RequestParam(required = false, defaultValue = "_") String username,
                                          @RequestParam(required = false, defaultValue = "_") String name,
                                          @RequestParam(required = false, defaultValue = "_") String email,
                                          @RequestParam(required = false) Long id,
                                          @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(defaultValue = "id,desc") String[] sort){
        try {
            Page<User> usersData = userService.findAllUser(username, name, email, id, page, size, sort);

            return new ResponseEntity<>(usersData, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

