package com.ecommerce.demo.repository;

import com.ecommerce.demo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<Account, Integer> {

    @Query(value = "SELECT * FROM account a Where a.username = :username", nativeQuery = true)
    List<Account> findByUsername(String username);
}
