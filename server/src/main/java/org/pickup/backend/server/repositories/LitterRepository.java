package org.pickup.backend.server.repositories;

import org.pickup.backend.server.models.Litter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LitterRepository extends JpaRepository<Litter, Long> {
}
