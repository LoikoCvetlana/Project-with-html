package com.sportoras.database.dto.reviewDto;

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
public class ReviewDto {

    private Long id;
    private User user;
    private String text;
    private LocalDate date;
    private long version;

    public ReviewDto(User user, String text, LocalDate date) {
        this.user = user;
        this.text = text;
        this.date = date;
    }
}
