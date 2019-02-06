package com.sportoras.service.dto.userDto;

import com.sportoras.database.entity.FullName;
import com.sportoras.database.entity.Material;
import com.sportoras.database.entity.UserDateil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateDto {

    private Long id;
    private FullName fullName;
    private String password;
    private String email;
    private LocalDate registrationDate;
    private String role;
}
