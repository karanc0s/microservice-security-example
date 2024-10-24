package com.karan.accountsservice.repository;

import com.karan.accountsservice.entity.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {


    Optional<Account> findByCustomerId(Long customerId);


    @Transactional
    @Modifying
    void deleteByCustomerId(Long customerId);

}
