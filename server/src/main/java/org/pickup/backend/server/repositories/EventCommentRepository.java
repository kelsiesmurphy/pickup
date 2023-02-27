package org.pickup.backend.server.repositories;

import org.pickup.backend.server.models.EventComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventCommentRepository extends JpaRepository<EventComment, Long> {
}
