package com.overone.KursTest.user;

import com.overone.KursTest.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByNameAndSurname(String name, String surname);
    User findFirstByNameAndPassword(String name, String password) ;
    User findFirstByNameAndSurnameAndRole(String name, String surname, Roles role) ;

}
