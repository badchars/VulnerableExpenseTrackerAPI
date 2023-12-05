package com.oy.expensetrackerapi.controller;


import com.oy.expensetrackerapi.entity.User;
import com.oy.expensetrackerapi.entity.UserModel;
import com.oy.expensetrackerapi.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(tags = "User API", description = "UserCRUD Operations")
public class UserController {

    @Autowired
    private UserService userService;



    @GetMapping("/users/{id}")
    public ResponseEntity<User> get(@PathVariable Long id){
        // TODO: Vulnerability-5 Misconfigured HTTP Headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Server", "Apache Tomcat 8");
        headers.add("Strict-Transport-Security", "max-age=63072000; includeSubDomains; preload");
        return new ResponseEntity<User>(userService.readUser(), headers, HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> update(@RequestBody User user, @PathVariable Long id, @RequestParam(required = false) String imageUrl){
        User mUser = userService.update(user, id, imageUrl);
        return new ResponseEntity<User>(mUser, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        userService.deleteUser();
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }


}
