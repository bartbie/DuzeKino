package org.duze.duzekino.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.duze.duzekino.model.Schedule;
import org.duze.duzekino.model.Schedule;
import org.duze.duzekino.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    final ScheduleRepository scheduleRepository;

    public void saveSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules() {
        return (List<Schedule>) scheduleRepository.findAll();
    }


    public Schedule findScheduleById(Long id) throws ScheduleNotFoundException {
        Optional<Schedule> result = scheduleRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new ScheduleNotFoundException("schedule not found with id: " + id);
    }

    public void deleteSchedule(Long id) throws ScheduleNotFoundException {
        Optional<Schedule> result = scheduleRepository.findById(id);
        if (result.isEmpty()) {
            throw new ScheduleNotFoundException("schedule not found with id: " + id);
        }
        scheduleRepository.deleteById(id);
    }
}
