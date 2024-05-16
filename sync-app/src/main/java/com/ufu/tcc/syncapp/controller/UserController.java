package com.ufu.tcc.syncapp.controller;

import com.ufu.tcc.commonsdomain.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {


    private JdbcUserDetailsManager jdbcUserDetailsManager;

    @Autowired
    public UserController(JdbcUserDetailsManager jdbcUserDetailsManager) {
        this.jdbcUserDetailsManager = jdbcUserDetailsManager;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> createUser(@RequestBody UserCreationRequest userCreationRequest) {

        UserDetails userDetails = User.builder()
                .username(userCreationRequest.username)
                .password(userCreationRequest.password)
                .roles(userCreationRequest.role.name())
                .build();


        jdbcUserDetailsManager.createUser(userDetails);

        return ResponseEntity.ok().build();
    }

    public record UserCreationRequest(String username, String password, Role role) {
    }


}
