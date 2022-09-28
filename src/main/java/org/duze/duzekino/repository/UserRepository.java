package org.duze.duzekino.repository;

import org.duze.duzekino.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Long countById(Long id);

    Optional<User> findUserByUsername(String username);
}
