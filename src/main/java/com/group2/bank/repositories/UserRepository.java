package com.group2.bank.repositories;

import com.group2.bank.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA Repository, these interface methods help use execute SQL queries
 * in the SQLite database bank0.db
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findById(long id);

    User findByUserName(String username);

}
