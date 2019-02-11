package com.sportoras.database.repository;

import com.sportoras.database.entity.User;
import com.sportoras.database.entity.UserDetail;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDetailRepository extends CrudRepository<UserDetail, Long> {

    List<UserDetail> findAll();

    UserDetail findByUser(User user);

    UserDetail findAllByUserEmail(String email);


//    @Query("update UserDetail u "
//            + "set u.company = ?1, u.phone = ?2, u.position = ?3, u.other_infomation = ?4"
//            + " where u.id = ?5")
//    UserDetail updateUserDetail
//            (@Param("company") String company, @Param("phone") String phone,
//             @Param("position") String position, @Param("otherInfomation") String other_infomation, @Param("id") Long id);
//

}