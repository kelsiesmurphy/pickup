package org.pickup.backend.server.repositories;

import org.pickup.backend.server.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends BaseRepository<Event, Long> {

    List<Event> findByCommunityId(long id);
}
