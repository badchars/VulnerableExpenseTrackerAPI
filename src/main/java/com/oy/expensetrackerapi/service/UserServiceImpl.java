package com.oy.expensetrackerapi.service;

import com.oy.expensetrackerapi.entity.User;
import com.oy.expensetrackerapi.entity.UserModel;
import com.oy.expensetrackerapi.exception.ItemAlreadyExistsException;
import com.oy.expensetrackerapi.exception.ResourceNotFoundException;
import com.oy.expensetrackerapi.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public User createUser(UserModel userModel) {
        if(userRepository.existsByEmail(userModel.getEmail())){
            throw new ItemAlreadyExistsException("User is already register with email: "+ userModel.getEmail());
        }
        User user = new User();
        BeanUtils.copyProperties(userModel, user);
        user.setPassword(bcryptEncoder.encode(user.getPassword()));

        System.out.println(user);
        return userRepository.save(user);
    }

    @Override
    public User readUser() throws RuntimeException{
        Long userId = getLoggedInUser().getId();
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found for the id: "+getLoggedInUser().getId()));
    }

    // TODO: CAUTION! Asagidaki metod zafiyete sebep olmaktadir. readUser() metodu kullanilmalidir.
    @Override
    public User read(Long id) throws ResourceNotFoundException {
        return userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found for the id: "+id));
    }

    @Override
    public User update(User user, Long id, String imageUrl) {
        // TODO: update metodu icerisinde read(Long id) metodu kullanilmaktadir. Bu metod SecurityContextHolder'i kullanmamaktadir. Yani cookie'den bagimsiz her kullaniciya ait bilgiler guncellenebilmektedir.
        User user1 = read(id);
        
        // TODO: Secure Code:
//        User user1  = readUser();

        user1.setName(user.getName() !=null ? user.getName() : user1.getName());
        user1.setEmail(user.getEmail() !=null ? user.getEmail() : user1.getEmail());
        user1.setPassword(user.getPassword() !=null ? bcryptEncoder.encode(user.getPassword()) : user1.getPassword());
        user1.setAge(user.getAge() !=null ? user.getAge() : user1.getAge());

        if(!StringUtils.isEmpty(imageUrl)){
            // TODO: VULNERABILITY-7 SSRF
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(imageUrl, String.class);
            user1.setUserEncodedImage(response);
        }

        return userRepository.save(user1);
    }

    @Override
    public void deleteUser() {
        User user = readUser();
        userRepository.delete(user);
    }

    @Override
    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        return userRepository.findByEmail(email).orElseThrow(()->
                new UsernameNotFoundException("User not found for the email"+email));
    }
}