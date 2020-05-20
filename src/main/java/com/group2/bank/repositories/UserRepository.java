package com.group2.bank.repositories;

import com.group2.bank.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

    User findById(long id);

    User findByUserName(String username);

}
