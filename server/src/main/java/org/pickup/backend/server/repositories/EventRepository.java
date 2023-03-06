package org.pickup.backend.server.repositories;

import org.pickup.backend.server.models.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends BaseRepository<Event, Long> {

    List<Event> findByCommunityId(long id);

    List<Event> findByEventDateTimeStartBefore(LocalDateTime datetime);

}
