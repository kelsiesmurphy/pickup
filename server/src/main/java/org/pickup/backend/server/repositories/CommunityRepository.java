package org.pickup.backend.server.repositories;

import org.pickup.backend.server.models.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface CommunityRepository extends BaseRepository<Community, Long> {

    List<Community> findByIsPrivate(Boolean isPrivate);
}
