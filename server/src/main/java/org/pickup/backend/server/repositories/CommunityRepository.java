package org.pickup.backend.server.repositories;

import org.pickup.backend.server.models.Community;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunityRepository extends JpaRepository<Community, Long> {

    List<Community> findByIsPrivate(Boolean isPrivate);
}
