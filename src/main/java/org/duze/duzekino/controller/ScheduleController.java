package org.duze.duzekino.controller;

import lombok.RequiredArgsConstructor;
import org.duze.duzekino.model.Schedule;
import org.duze.duzekino.service.ScheduleNotFoundException;
import org.duze.duzekino.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("api/v1/Schedule")
@RequiredArgsConstructor
public class ScheduleController {

    final ScheduleService scheduleService;

    @GetMapping("/find")
    public Schedule getScheduleById(@RequestParam Long id) throws ScheduleNotFoundException {
        return scheduleService.findScheduleById(id);
    }

    @PostMapping("/save")
    public Schedule saveSchedule(@RequestBody Schedule schedule) throws ScheduleNotFoundException{
        scheduleService.saveSchedule(schedule);
        return scheduleService.findScheduleById(schedule.getScheduleId());
    }


}
