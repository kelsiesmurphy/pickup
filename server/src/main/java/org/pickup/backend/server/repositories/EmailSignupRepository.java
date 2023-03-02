package org.pickup.backend.server.repositories;

import org.pickup.backend.server.models.EmailSignup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailSignupRepository extends BaseRepository<EmailSignup, Long> {
}
