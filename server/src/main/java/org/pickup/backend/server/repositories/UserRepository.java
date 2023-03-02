package org.pickup.backend.server.repositories;

import org.pickup.backend.server.models.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends BaseRepository<User, Long> {

    List<User> findByCommunityId(long id);

}
