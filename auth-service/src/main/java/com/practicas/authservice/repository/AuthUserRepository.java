package com.practicas.authservice.repository;

import com.practicas.authservice.entity.AuthUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Integer> {

  Optional<AuthUser>findByUserName(String username);
}
