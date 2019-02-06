package com.sportoras.database.repository;

import com.sportoras.database.entity.UserDateil;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDateilRepository extends CrudRepository<UserDateil, Long> {

    List<UserDateil> findAll();

    UserDateil findAllByUserEmail(String email);

}