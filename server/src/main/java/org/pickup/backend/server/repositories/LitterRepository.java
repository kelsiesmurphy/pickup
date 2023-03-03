package org.pickup.backend.server.repositories;

import org.pickup.backend.server.models.Litter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LitterRepository extends BaseRepository<Litter, Long> {



}
