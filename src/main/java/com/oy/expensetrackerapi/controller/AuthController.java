package com.oy.expensetrackerapi.controller;

import com.oy.expensetrackerapi.entity.AuthModel;
import com.oy.expensetrackerapi.entity.JwtResponse;
import com.oy.expensetrackerapi.entity.User;
import com.oy.expensetrackerapi.entity.UserModel;
import com.oy.expensetrackerapi.security.CustomUserDetailsService;
import com.oy.expensetrackerapi.service.UserService;
import com.oy.expensetrackerapi.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("/v1")
@Api(tags = "Login API", description = "User/Admin login apis")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokeUtil;


    // --------------------------------------------------------------------------------------------------------------------------------------------
    // TODO: Session Based AuthN
//    @PostMapping("/login")
//    public ResponseEntity<HttpStatus> login(@RequestBody AuthModel authModel){
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authModel.getEmail(), authModel.getPassword()));
//        // TODO: VULNERABILITY -> (Session Based AuthN) SecurityContextHolder JSESSIONID olusturuyor. Bu session ile Cyprox'ta 2 farkli kullanici authN tanimlanarak IDOR'lar yakalanabilir.
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
//    }
    // --------------------------------------------------------------------------------------------------------------------------------------------



    

    // --------------------------------------------------------------------------------------------------------------------------------------------
    // TODO: Token Based AuthN 1./2

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody AuthModel authModel) throws Exception{
        authenticate(authModel.getEmail(), authModel.getPassword());

        // We need to generate the jwt token
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(authModel.getEmail());

        final String token = jwtTokeUtil.generateToken(userDetails);

        return new ResponseEntity<JwtResponse>(new JwtResponse(token),HttpStatus.OK);
    }



    private void authenticate(String email, String password) throws Exception {

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        }   catch (DisabledException e){
                throw new Exception("User disabled");
        }   catch (BadCredentialsException e) {
                throw new Exception("Bad Credential");
        }

    }
     //--------------------------------------------------------------------------------------------------------------------------------------------


    @PostMapping("/register")
    public ResponseEntity<User> save(@Valid @RequestBody UserModel userModel) {
        return new ResponseEntity<User>(userService.createUser(userModel), HttpStatus.CREATED);
    }
}

