package com.megapet.backendmegapet.user.domain.persistence;

import com.megapet.backendmegapet.user.domain.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAndPassword(String email, String password);
}
