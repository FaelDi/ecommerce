package com.rafastech.media.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;
import com.rafastech.media.ecommerce.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @NativeQuery(value = "SELECT * FROM public.users WHERE user_id = :id")
    Optional<User> findByUserId(@Param("id") UUID userId);

    @NativeQuery(value = "SELECT * FROM public.users WHERE email = :email")
    Optional<User> findByEmail(@Param("email") String email);
}