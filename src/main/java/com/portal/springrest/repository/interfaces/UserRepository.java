package com.portal.springrest.repository.interfaces;

import com.portal.springrest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
