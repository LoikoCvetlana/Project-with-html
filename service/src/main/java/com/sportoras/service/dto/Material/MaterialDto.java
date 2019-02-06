package com.sportoras.service.dto.Material;

import com.sportoras.database.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaterialDto {

    private Long id;
    private String name;
    private String description;
    private List<Product> products = new ArrayList<>();
}