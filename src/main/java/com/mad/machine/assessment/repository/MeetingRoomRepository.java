package com.mad.machine.assessment.repository;

import com.mad.machine.assessment.model.MeetingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MeetingRoomRepository extends JpaRepository<MeetingRoom, Integer> {

    Optional<MeetingRoom> findByRoomName(String roomName);
}
