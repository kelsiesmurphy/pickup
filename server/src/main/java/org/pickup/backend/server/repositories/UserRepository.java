package org.pickup.backend.server.repositories;

import org.pickup.backend.server.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
