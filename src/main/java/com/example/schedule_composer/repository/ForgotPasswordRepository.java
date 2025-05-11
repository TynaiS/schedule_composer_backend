package com.example.schedule_composer.repository;

import com.example.schedule_composer.entity.ForgotPassword;
import com.example.schedule_composer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Long> {
    Optional<ForgotPassword> findByUser(User user);

    @Query("select fp from ForgotPassword fp where fp.verificationCode = ?1 and fp.user = ?2")
    Optional<ForgotPassword> findByVerificationCodeAndUser(String verificationCode, User user);
}
