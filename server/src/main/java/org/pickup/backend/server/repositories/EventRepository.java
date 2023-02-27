package org.pickup.backend.server.repositories;

import org.pickup.backend.server.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
