package com.budan.springappblog.security.service;

import com.budan.springappblog.model.User;
import com.budan.springappblog.repository.UserRepository;
import com.budan.springappblog.security.model.JwtUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.json.JsonException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {
        User user = this.userRepository.findByFirstname(firstName);

        return Optional.ofNullable(user)
                .map(JwtUserDetails::new)
                .orElseThrow(() -> new JsonException("User not found"));
    }
}
