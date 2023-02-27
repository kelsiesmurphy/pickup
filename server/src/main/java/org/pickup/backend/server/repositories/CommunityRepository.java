package org.pickup.backend.server.repositories;

import org.pickup.backend.server.models.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community, Long> {
}
