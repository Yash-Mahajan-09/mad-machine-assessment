package com.mad.machine.assessment.serivce.impl;

import com.mad.machine.assessment.exception.ConflictException;
import com.mad.machine.assessment.exception.ServiceException;
import com.mad.machine.assessment.model.Meeting;
import com.mad.machine.assessment.model.MeetingRoom;
import com.mad.machine.assessment.model.User;
import com.mad.machine.assessment.repository.MeetingRepository;
import com.mad.machine.assessment.repository.MeetingRoomRepository;
import com.mad.machine.assessment.repository.UserRepository;
import com.mad.machine.assessment.response.ServiceResponse;
import com.mad.machine.assessment.serivce.MeetingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MeetingRoomRepository meetingRoomRepository;


    @Override
    @Transactional
    public ServiceResponse<String> scheduleSinglePersonMeeting(int organizerId, LocalDate date, LocalTime startTime, LocalTime endTime) {
        log.info("Check for meeting collisions for the organizer: {}", organizerId);
        List<Meeting> collisions = meetingRepository.findParticipantCollisions(organizerId, date, startTime, endTime);
        if (!collisions.isEmpty()) {
            log.error("Meeting time collides with an existing one.");
            throw new ConflictException("Meeting time collides with an existing one.");
        }

        log.info("Schedule the meeting if no collisions");
        User organizer = userRepository.findById(organizerId).orElseThrow(() -> new RuntimeException("Organizer not found"));
        Meeting meeting = new Meeting();
        meeting.setOrganizer(organizer);
        meeting.setDate(date);
        meeting.setStartTime(startTime);
        meeting.setEndTime(endTime);

        meetingRepository.save(meeting);
        log.info("Schedule meeting successfully");
        return new ServiceResponse<>("Single-person meeting scheduled successfully", HttpStatus.OK, null);
    }

    @Override
    @Transactional
    public ServiceResponse<String> scheduleMultiPersonMeeting(int organizerId, List<Integer> participantIds,
                                                              LocalDate date, LocalTime startTime, LocalTime endTime, String roomName) {
        log.info("Check room availability for roomName : {}", roomName);
        List<Meeting> roomCollisions = meetingRepository.findRoomCollisions(roomName, date, startTime, endTime);
        if (!roomCollisions.isEmpty()) {
            throw new ConflictException("Room is not available during this time.");
        }

        log.info("Include organizer in the participant list");
        Set<Integer> allParticipants = new HashSet<>(participantIds);
        allParticipants.add(organizerId);

        log.info("Check participant availability");
        for (int participantId : allParticipants) {
            List<Meeting> participantCollisions = meetingRepository.findParticipantCollisions(participantId, date, startTime, endTime);
            if (!participantCollisions.isEmpty()) {
                log.error("Participant with ID: {} is unavailable.", participantId);
                throw new ConflictException("Participant with ID " + participantId + " is unavailable.");
            }
        }

        log.info("Schedule the meeting if no collisions : {}", roomName);
        MeetingRoom room = meetingRoomRepository.findByRoomName(roomName).orElseThrow(() -> new ServiceException("Room not found"));
        User organizer = userRepository.findById(organizerId).orElseThrow(() -> new ServiceException("Organizer not found"));

        Meeting meeting = new Meeting();
        meeting.setRoom(room);
        meeting.setOrganizer(organizer);
        meeting.setDate(date);
        meeting.setStartTime(startTime);
        meeting.setEndTime(endTime);

        Set<User> participants = new HashSet<>();
        for (int participantId : allParticipants) {
            User participant = userRepository.findById(participantId).orElseThrow(() -> new RuntimeException("Participant not found"));
            participants.add(participant);
        }
        meeting.setParticipants(participants);

        meetingRepository.save(meeting);
        log.info("Schedule meeting successfully");
        return new ServiceResponse<>("Multi-person meeting scheduled successfully", HttpStatus.OK, null);
    }
}
