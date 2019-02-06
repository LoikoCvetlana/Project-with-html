package com.sportoras.service.service;

import com.sportoras.database.repository.UserRepository;
import com.sportoras.service.converter.UserDetailsConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))

public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private final UserDetailsConverter detailsConverter;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(detailsConverter::convert)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}