package com.sportoras.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
