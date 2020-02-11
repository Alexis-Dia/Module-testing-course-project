package com.moduleTesting.portal.moduleTesting.rest.security;

import com.moduleTesting.portal.dto.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImplementation implements UserDetailsService {

/*
    @Autowired
    private UserRepository userRepository;
*/

    @Override
    public UserDetails loadUserByUsername(String username) {
        if ( username == null || username.isEmpty() ){
            //throw new UsernameNotFoundException("username is empty");
        }

        //com.User foundUser = userRepository.findByEmailAddress(username);
        User foundUser = new User("Alex", "12345678");
        //if( foundUser != null ){
            System.out.println("FOUND");
            return new SecurityUser(foundUser.getEmailAddress(), foundUser.getPassword(), foundUser.getRole().name());

        //}
        //throw new UsernameNotFoundException( username + "is not found");
    }
}