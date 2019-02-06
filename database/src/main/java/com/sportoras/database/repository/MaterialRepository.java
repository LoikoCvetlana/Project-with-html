package com.sportoras.database.repository;

import com.sportoras.database.entity.Material;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MaterialRepository extends CrudRepository<Material, Long> {

    List<Material> findAll();

    Material findByName(String name);

    Material findMaterialById(Long id);

//    @Modifying
//    @Query("update Material m set m.name = :name, m.description = :name where m.id = :id")
//    int changeMaterial (@Param("name") String name, @Param("description") String description);

}