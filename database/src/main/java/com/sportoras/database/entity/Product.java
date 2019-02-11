package com.sportoras.database.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@ToString(exclude = "material")
@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product", schema = "oraz_storage")
public class Product extends BaseEntity<Long> {

    private String name;

    @Column(name = "article", unique = true)
    private String article;

    private String picture;

    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private Material material;

    public Product(String name) {
        this.name = name;
    }
}
