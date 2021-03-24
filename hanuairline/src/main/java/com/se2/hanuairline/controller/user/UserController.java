package com.se2.hanuairline.controller.user;

import com.se2.hanuairline.model.user.User;
import com.se2.hanuairline.payload.user.UserPayload;
import com.se2.hanuairline.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
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

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getUserByID(@PathVariable Long id){
        User user = userService.getUserById(id);

        if(user == null){
            return new ResponseEntity<>("Wrong id or logic error", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody UserPayload request){
        User user = userService.updateUser(id, request);

        if(user == null){
            return new ResponseEntity<>("Wrong user id", HttpStatus.NOT_MODIFIED);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        if (userService.deleteUser(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}

