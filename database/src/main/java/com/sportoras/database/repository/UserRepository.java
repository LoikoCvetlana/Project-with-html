package com.sportoras.database.repository;

import com.sportoras.database.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByEmail(String email);

    List<User> findAllByUserDateilCompany(String compony);

    User findUserById(Long id);

    Optional<User> findByEmail(String email);

    List<User> findAll();

}