package com.se2.hanuairline.service.user;

import com.se2.hanuairline.model.user.User;
import com.se2.hanuairline.repository.user.UserRepository;
import com.se2.hanuairline.util.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Page<User> findAllUser(String username, String name, String email, Long id, int page, int size, String[] sort){
        Pageable pagingSort = PaginationUtils.pagingSort(page, size, sort);

        if(id != null){
            return userRepository.findById(id, pagingSort);
        }

        return userRepository.findByEmailContainingAndUsernameContainingAndNameContaining(email, username, name, pagingSort);
    }

}
