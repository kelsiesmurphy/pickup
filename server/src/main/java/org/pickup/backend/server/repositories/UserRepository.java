package org.pickup.backend.server.repositories;

import org.pickup.backend.server.models.User;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Long> {

    List<User> findByCommunityId(long id);

    List<User> findByCreateDateBeforeAndCommunityIdEquals(LocalDate date, Long communityId);

    Optional<User> findByAuth0Id(String id);

}
