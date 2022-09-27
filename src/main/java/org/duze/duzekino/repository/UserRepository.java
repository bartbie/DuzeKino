package org.duze.duzekino.repository;

import org.duze.duzekino.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Long countById(Long id);

    User findUserByUsername(String username);
}
