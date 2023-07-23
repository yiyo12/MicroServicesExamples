package com.authjwt.repository;

import com.authjwt.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<AuthUser, Integer> {
    /**
     * @param userName
     * @return
     */
    Optional<AuthUser> findByUserName(String userName);
}
