package org.duze.duzekino.repository;

import org.duze.duzekino.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    Long countById(Integer id);

    User findUserByUsername(String username);
}
