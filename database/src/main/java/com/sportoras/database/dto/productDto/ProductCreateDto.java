package com.sportoras.database.dto.productDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCreateDto {

    private Long id;
    private String name;
    private String article;
    private String picture;
    private BigDecimal value;
    private String material;

    public ProductCreateDto(String name, String article, String picture, BigDecimal value, String material) {
        this.name = name;
        this.article = article;
        this.picture = picture;
        this.value = value;
        this.material = material;
    }
}
