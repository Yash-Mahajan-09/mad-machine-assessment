package com.mad.machine.assessment.controller;

import com.mad.machine.assessment.dto.ScheduleMeetingDto;
import com.mad.machine.assessment.response.ServiceResponse;
import com.mad.machine.assessment.serivce.MeetingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/meetings")
@Slf4j
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @PostMapping("/schedule")
    public ResponseEntity<ServiceResponse<String>> scheduleSinglePersonMeeting(@RequestBody ScheduleMeetingDto scheduleMeetingDto) {
        log.info("Scheduling Single-Person Meeting for Organizer Id: {}", scheduleMeetingDto.getOrganizerId());
        ServiceResponse<String> response = meetingService.scheduleSinglePersonMeeting(scheduleMeetingDto.getOrganizerId(),
                scheduleMeetingDto.getDate(), scheduleMeetingDto.getStartTime(), scheduleMeetingDto.getEndTime());
        log.info("Successfully scheduled Single-Person Meeting for Organizer Id: {}", scheduleMeetingDto.getOrganizerId());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/scheduleAdvanced")
    public ResponseEntity<ServiceResponse<String>> scheduleMultiPersonMeeting(@RequestBody ScheduleMeetingDto scheduleMeetingDto) {
        log.info("Scheduling Multi-Person Meeting for Room Name: {}", scheduleMeetingDto.getRoomName());
        ServiceResponse<String> response = meetingService.scheduleMultiPersonMeeting(scheduleMeetingDto.getOrganizerId(),
                scheduleMeetingDto.getParticipantIds(), scheduleMeetingDto.getDate(), scheduleMeetingDto.getStartTime(),
                scheduleMeetingDto.getEndTime(), scheduleMeetingDto.getRoomName());
        log.info("Successfully Scheduled Multi-Person Meeting for Room Name: {}", scheduleMeetingDto.getRoomName());
        return ResponseEntity.ok(response);
    }
}
