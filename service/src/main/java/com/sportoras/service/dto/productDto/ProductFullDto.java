package com.sportoras.service.dto.productDto;

import com.sportoras.database.entity.Material;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductFullDto {

    private Long id;
    private String name;
    private String article;
    private String picture;
    private BigDecimal value;
    private Material material;
}