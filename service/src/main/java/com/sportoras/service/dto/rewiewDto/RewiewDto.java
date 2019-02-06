package com.sportoras.service.dto.rewiewDto;

import com.sportoras.database.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RewiewDto {

    private Long id;
    private User user;
    private String text;
    private LocalDate date;
    private long version;
}
