package com.moduleTesting.portal.rest.security;

import com.moduleTesting.portal.dto.UserDto;
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
        UserDto foundUserDto = new UserDto("Alex", "12345678");
        //if( foundUser != null ){
            System.out.println("FOUND");
            return new SecurityUser(foundUserDto.getEmailAddress(), foundUserDto.getPassword(), foundUserDto.getRole().name());

        //}
        //throw new UsernameNotFoundException( username + "is not found");
    }
}