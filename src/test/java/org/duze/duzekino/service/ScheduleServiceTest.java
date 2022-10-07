package org.duze.duzekino.service;

import org.assertj.core.api.Assertions;
import org.duze.duzekino.model.Schedule;
import org.duze.duzekino.repository.ScheduleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;


@SpringBootTest
public class ScheduleServiceTest {

    @Autowired
    ScheduleService scheduleService;
    @Autowired
    ScheduleRepository scheduleRepo;

    //set up the default state of database before each test method
    @BeforeEach
    void setUp() {
        Schedule schedule = new Schedule(true, true, true, true, true, true, true);
        scheduleRepo.deleteAll();
        scheduleRepo.save(schedule);
    }

    @Test
    public void testAddSchedule() throws ScheduleNotFoundException {
        Schedule schedule = new Schedule(true, true, true, true, true, true, true);
        scheduleService.saveSchedule(schedule);
        List<Schedule> schedules = (List<Schedule>) scheduleService.getAllSchedules();
        Long scheduleId = schedules.get(schedules.size() - 1).getScheduleId();
        Schedule savedSchedule = scheduleService.findScheduleById(scheduleId);
        Assertions.assertThat(savedSchedule).isNotNull();
    }

    @Test
    public void testListAll() {
        Iterable<Schedule> schedules = scheduleService.getAllSchedules();
        Assertions.assertThat(schedules).hasSizeGreaterThan(0);
        for (Schedule schedule : schedules
        ) {
            System.out.println(schedules);
        }
    }

    @Test
    public void testUpdate() throws ScheduleNotFoundException {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        Schedule lastSchedule = schedules.get(schedules.size() - 1);
        Long scheduleId = lastSchedule.getScheduleId();
        Schedule schedule = scheduleService.findScheduleById(scheduleId);
        schedule.setMon(false);
        scheduleService.saveSchedule(schedule);
        Schedule updatedSchedule = scheduleService.findScheduleById(scheduleId);
        Assertions.assertThat(updatedSchedule.isMon()).isEqualTo(false);
    }

    @Test
    public void testGet() throws ScheduleNotFoundException {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        Schedule lastSchedule = schedules.get(schedules.size() - 1);
        Long scheduleId = lastSchedule.getScheduleId();
        Schedule schedule = scheduleService.findScheduleById(scheduleId);
        Assertions.assertThat(schedule).isNotNull();
        System.out.println(schedule);
    }

    @Test
    public void testDelete() throws ScheduleNotFoundException {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        Schedule lastSchedule = schedules.get(schedules.size() - 1);
        Long scheduleId = lastSchedule.getScheduleId();
        scheduleService.deleteSchedule(scheduleId);
        Optional<Schedule> optional = scheduleRepo.findById(scheduleId);
        Assertions.assertThat(optional).isNotPresent();
    }
}
