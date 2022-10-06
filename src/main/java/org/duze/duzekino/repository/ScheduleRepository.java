package org.duze.duzekino.repository;

import lombok.RequiredArgsConstructor;
import org.duze.duzekino.model.Schedule;
import org.duze.duzekino.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
