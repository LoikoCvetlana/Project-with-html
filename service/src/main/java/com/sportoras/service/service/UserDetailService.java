package com.sportoras.service.service;

import com.sportoras.database.entity.UserDateil;
import com.sportoras.database.repository.UserDateilRepository;
import com.sportoras.database.repository.UserRepository;
import com.sportoras.service.dto.userDto.UserDetailCreateDto;
import com.sportoras.service.dto.userDto.UserDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailService {

    private final UserDateilRepository userDateilRepository;
    private final UserRepository userRepository;


    @Transactional
    public UserDateil saveUserDetail(UserDetailCreateDto userDetailCreateDto) {
        return userDateilRepository.save(UserDateil.builder()
                .user(userDetailCreateDto.getUser())
                .company(userDetailCreateDto.getCompany())
                .position(userDetailCreateDto.getPosition())
                .phone(userDetailCreateDto.getPhone())
                .otherInformation(userDetailCreateDto.getOtherInformation())
                .build());
    }
}