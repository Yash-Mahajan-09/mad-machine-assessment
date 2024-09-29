CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE MeetingRooms (
    room_id INT AUTO_INCREMENT PRIMARY KEY,
    room_name VARCHAR(100),
    capacity INT
);

CREATE TABLE Meetings (
    meeting_id INT AUTO_INCREMENT PRIMARY KEY,
    room_id INT,
    organizer_id INT,
    start_time TIME,
    end_time TIME,
    date DATE,
    CONSTRAINT fk_room FOREIGN KEY (room_id) REFERENCES MeetingRooms(room_id),
    CONSTRAINT fk_organizer FOREIGN KEY (organizer_id) REFERENCES Users(user_id),
    CONSTRAINT no_overlap UNIQUE (room_id, date, start_time, end_time)
);

CREATE TABLE MeetingParticipants (
    meeting_id INT,
    user_id INT,
    PRIMARY KEY (meeting_id, user_id),
    CONSTRAINT fk_meeting FOREIGN KEY (meeting_id) REFERENCES Meetings(meeting_id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES Users(user_id)
);
