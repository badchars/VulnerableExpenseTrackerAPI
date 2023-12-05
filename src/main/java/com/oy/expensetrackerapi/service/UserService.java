package com.oy.expensetrackerapi.service;

import com.oy.expensetrackerapi.entity.User;
import com.oy.expensetrackerapi.entity.UserModel;

public interface UserService {

    User createUser(UserModel userModel);

    User readUser();

    User read(Long id);

    User update(User user, Long id, String imageUrl);

    void deleteUser();

    User getLoggedInUser();
    
}
