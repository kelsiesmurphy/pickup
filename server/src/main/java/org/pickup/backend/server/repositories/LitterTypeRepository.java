package org.pickup.backend.server.repositories;

import org.pickup.backend.server.models.LitterType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LitterTypeRepository extends JpaRepository<LitterType, Long> {
}
