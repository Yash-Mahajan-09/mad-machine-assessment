package com.mad.machine.assessment.serivce;

import com.mad.machine.assessment.response.ServiceResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public interface MeetingService {

     ServiceResponse<String> scheduleSinglePersonMeeting(int organizerId, LocalDate date,
                                                         LocalTime startTime, LocalTime endTime);

      ServiceResponse<String> scheduleMultiPersonMeeting(int organizerId, List<Integer> participantIds,
                                                         LocalDate date, LocalTime startTime, LocalTime endTime, String roomName) ;
}
