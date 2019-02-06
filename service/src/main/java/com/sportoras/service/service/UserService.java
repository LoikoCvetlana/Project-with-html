package com.sportoras.service.service;

import com.sportoras.database.entity.User;
import com.sportoras.database.repository.UserRepository;
import com.sportoras.service.dto.userDto.UserBasicDto;
import com.sportoras.service.dto.userDto.UserCreateDto;
import com.sportoras.service.dto.userDto.UserFullDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;

    public User findUserById(Long userId) {
        return userRepository.findUserById(userId);
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public List<UserBasicDto> allUsers() {
        return userRepository.findAll().stream()
                .map(it -> new UserBasicDto(it.getId(), it.getFullName(), it.getRole(), it.getUserDateil(), it.getRegistrationDate()))
                .collect(Collectors.toList());
    }

//    @Transactional
//    public User registerNewUserAccount(UserCreateDto accountDto){
//        User user = new User();
//        user.setFullName(accountDto.getFullName());
//        user.setRegistrationDate(LocalDate.now());
//        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
//        user.setEmail(accountDto.getEmail());
//        user.setRole("User");
//        return userRepository.save(user);
//    }

//    @Transactional
//    public UserFullDto saveUser(UserCreateDto userCreateDto) {
//        User sevedUser = userRepository.save(
//                User.builder()
//                        .fullName(userCreateDto.getFullName())
//                        .email(userCreateDto.getEmail())
//                        .password(userCreateDto.getPassword())
//                        .registrationDate(LocalDate.now())
//                        .role("User")
//                        .build());
//
//        return UserFullDto.builder()
//                .id(sevedUser.getId())
//                .fullName(sevedUser.getFullName())
//                .email(sevedUser.getEmail())
//                .password(sevedUser.getPassword())
//                .userDateil(sevedUser.getUserDateil())
//                .registrationDate(LocalDate.now())
//                .build();
//    }
}