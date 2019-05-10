package com.loya.onetomanybidirectionaldemo.service;

import com.loya.onetomanybidirectionaldemo.entity.User;
import com.loya.onetomanybidirectionaldemo.exception.UsernameAlreadyExistException;
import com.loya.onetomanybidirectionaldemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser (User newUser){

        try{
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

            //username must be unique
            newUser.setUsername(newUser.getUsername());
            // password must be match with confirm password
             newUser.setConfirmPassword("");
            //we dont show confirm password

            return userRepository.save(newUser);



        } catch (Exception e){
            throw new UsernameAlreadyExistException("username '"+newUser.getUsername()+"' already exist");
        }


    }


}
