package com.portal.springrest.repository.interfaces;

import com.portal.springrest.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByName(String name);

}
