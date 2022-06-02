package com.example.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    public CustomUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//            User loaduser = userRepository.findUserByUsername(username);
//        if(loaduser ==null) {
//            throw new UsernameNotFoundException("User Not Found");
//        }
//        UserDetails user = org.springframework.security.core.userdetails.User.withUsername(loaduser.getUsername()).password(loaduser.getPassword()).authorities("USER").build();
//        return user;
//    }
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        com.example.student.User loaduser = userRepository.findUserByUsername(userName);
        if(loaduser ==null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        UserDetails user = User.withUsername(loaduser.getUsername())
                          .password(loaduser.getPassword())
                          .authorities(loaduser.getRole()).build();
        System.out.println(user);
        return user;
    }
}


