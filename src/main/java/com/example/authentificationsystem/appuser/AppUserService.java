package com.example.authentificationsystem.appuser;

import com.example.authentificationsystem.registration.token.ConfirmationToken;
import com.example.authentificationsystem.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
    private final static String USER_NOT_FOUND_MSG = " User with email %s not found";
    private final UserRepository userRepository ;
    private ConfirmationTokenService confirmationTokenService ;
    private final appUser appuser ;
    private final BCryptPasswordEncoder bCryptPasswordEncoder ;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(()->
                new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG , email )));
    }
    public String signUpUser(appUser user) {
        boolean UserExists = userRepository.findByEmail(user.getEmail()).isPresent() ;
        if(UserExists) throw new IllegalStateException("Email already existing ") ;
        String EncodedPassword = bCryptPasswordEncoder.encode(user.getPassword()) ;
        user.setPassword(EncodedPassword);
        userRepository.save(user) ;
        String token = UUID.randomUUID().toString() ;

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token ,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15) ,
                appuser
        );
     confirmationTokenService.saveConfirmationToken(confirmationToken) ;
         return "it wworks  " ;
    }
    public int enableAppUser(String email) {
        return userRepository.enableAppUser(email);
    }
}
