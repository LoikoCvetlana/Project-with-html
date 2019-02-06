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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@ToString(exclude = "product")
@Data
@Builder
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Entity
@Table(name = "material", schema = "oraz_storage")
public class Material extends BaseEntity<Long> {

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    private String description;

    @OneToMany
    private List<Product> products;

    public Material(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Material(Long id, String name, String description, List<Product> products) {
        super();
        this.name = name;
        this.description = description;
        this.products = products;
    }
}

