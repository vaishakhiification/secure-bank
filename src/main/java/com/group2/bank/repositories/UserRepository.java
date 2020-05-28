package com.group2.bank.repositories;

import com.group2.bank.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findById(long id);

    User findByUserName(String username);



}
