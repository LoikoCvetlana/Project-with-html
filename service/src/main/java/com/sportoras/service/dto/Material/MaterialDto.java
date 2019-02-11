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

    public MaterialDto(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    private String description;
    private List<Product> products = new ArrayList<>();

    public MaterialDto(String name, String description) {
        this.name = name;
        this.description = description;
    }
}