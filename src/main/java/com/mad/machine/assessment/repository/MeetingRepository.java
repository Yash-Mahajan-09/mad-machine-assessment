package com.mad.machine.assessment.repository;

import com.mad.machine.assessment.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Integer> {

    // Check for room availability
    @Query("SELECT m FROM Meeting m WHERE m.room.roomName = :roomName AND m.date = :date " +
           "AND NOT (:endTime <= m.startTime OR :startTime >= m.endTime)")
    List<Meeting> findRoomCollisions(@Param("roomName") String roomName,
                                     @Param("date") LocalDate date,
                                     @Param("startTime") LocalTime startTime,
                                     @Param("endTime") LocalTime endTime);

    // Check for participant availability
    @Query("SELECT m FROM Meeting m JOIN m.organizer p WHERE p.userId = :userId AND m.date = :date " +
           "AND NOT (:endTime <= m.startTime OR :startTime >= m.endTime)")
    List<Meeting> findParticipantCollisions(@Param("userId") int userId,
                                            @Param("date") LocalDate date,
                                            @Param("startTime") LocalTime startTime,
                                            @Param("endTime") LocalTime endTime);
}
