package com.oy.expensetrackerapi.controller;


import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/v20")
@Api(tags = "User API", description = "UserCRUD Operations")
public class VersionController {

    @GetMapping("/improperAssetsManagementVersionEndpoint")
    public ResponseEntity improperAssetsManagementVersion() {
        return new ResponseEntity(HttpStatus.OK);
    }


}
