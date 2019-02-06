package com.sportoras.database.repository;

import com.sportoras.database.entity.Rewiew;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RewiewRepository extends CrudRepository<Rewiew, Long> {

    List<Rewiew> findAllByDate(LocalDate date);

    List<Rewiew> findAllByUserEmail(String email);

    List<Rewiew> findAll();

    @Query("select r "
            + "from Rewiew r "
            + "join r.user u "
            + "join u.userDateil ud "
            + "where r.date > :date and u.email = :email and ud.company = :company")
    List<Rewiew> filtersRewiew(@Param("date") LocalDate date, @Param("email") String email, @Param("company") String company);

    @Query("delete  "
            + "from Rewiew r "
            + "where r.id = :userId")
    int deleteAdminDetails(@Param("userId") Long id);





//    @Modifying
//    @Query(value = "UPDATE computer_games_e_shop_storage.admin_detail SET salary = :salary WHERE user_id = :userId", nativeQuery = true)
//    int changeSalary(@Param("userId") Long userId, @Param("salary") Integer salary);
//
//
//
//    @Modifying
//    @Query("update User u set u.lastName = :lastName where u.login = :login")
//    int changeLastName(@Param("login") String login, @Param("lastName") String lastName);
//
//    @Modifying
//    @Query("update User u set u.email = :email where u.login = :login")
//    int changeEmail(@Param("login") String login, @Param("email") String email);
//
//    @Modifying
//    @Query("update User u set u.phoneNumber = :phoneNumber where u.login = :login")
//    int changePhoneNumber(@Param("login") String login, @Param("phoneNumber") String phoneNumber);
//
//    @Modifying
//    @Query("update User u set u.address = :address where u.login = :login")
//    int changeAddress(@Param("login") String login, @Param("address") String address);
}