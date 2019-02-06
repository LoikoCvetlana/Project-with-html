package com.sportoras.service.service;

import com.sportoras.database.entity.Rewiew;
import com.sportoras.database.repository.RewiewRepository;
import com.sportoras.service.dto.rewiewDto.RewiewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RewiewService {

    private final RewiewRepository rewiewRepository;


    public List<RewiewDto> allRewiews() {
        return rewiewRepository.findAll().stream()
                .map(it -> new RewiewDto(it.getId(), it.getUser(), it.getText(), it.getDate(), it.getVersion()))
                .collect(Collectors.toList());
    }

    @Transactional
    public Rewiew saveRewiew(RewiewDto rewiewDto) {
        return rewiewRepository.save(Rewiew.builder()
                .user(rewiewDto.getUser())
                .date(LocalDate.now())
                .text(rewiewDto.getText())
                .version(1L)
                .build());
    }
}