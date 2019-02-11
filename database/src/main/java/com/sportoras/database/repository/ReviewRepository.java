package com.sportoras.database.repository;

import com.sportoras.database.entity.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends CrudRepository<Review, Long> {

    List<Review> findAllByUserEmail(String email);

    List<Review> findAll();

    @Query("select r "
            + "from Review r "
            + "join r.user u "
            + "join u.userDetail ud "
            + "where r.date > :date and u.email = :email and ud.company = :company")
    List<Review> filtersReview(@Param("date") LocalDate date, @Param("email") String email, @Param("company") String company);

    void deleteById(Long id);

    Optional<Review> findById (Long id);

}