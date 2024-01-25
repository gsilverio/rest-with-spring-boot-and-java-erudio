package com.restjava.RestJava.repositories;

import com.restjava.RestJava.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User WHERE u.useName = :userName")
    User findByUsername(@Param("userName") String userName);
}
