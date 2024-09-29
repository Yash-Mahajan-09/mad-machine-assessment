package com.mad.machine.assessment.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class ScheduleMeetingDto implements Serializable {

    private static final long serialVersionUID = 1234567L;
    private int organizerId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private List<Integer> participantIds;
    private String roomName;
}
