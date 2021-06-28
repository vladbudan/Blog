package com.budan.springappblog.repository;

import com.budan.springappblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByFirstname(String firstName);

    User findByEmail(String email);
}
