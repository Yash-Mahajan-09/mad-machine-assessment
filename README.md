This is a Spring Boot project.

I have used REST API with Spring Web and an H2 in-memory database.

You can open it in IntelliJ or STS.

If you want to run it directly, I have shared the JAR via email, or you can run it through your IDE.

After starting the application, access the H2 database at the following login URL: http://localhost:8090/h2-console.

After logging in DB, run the following queries:

-- Insert Users
INSERT INTO Users (name) VALUES ('Alice');
INSERT INTO Users (name) VALUES ('Bob');
INSERT INTO Users (name) VALUES ('Charlie');
INSERT INTO Users (name) VALUES ('Yash');
INSERT INTO Users (name) VALUES ('Aman');


-- Insert metadata for MeetingRoom

INSERT INTO meeting_rooms (room_name, capacity) VALUES ('Conference Room A', 5);
INSERT INTO meeting_rooms (room_name, capacity) VALUES ('Conference Room B', 10);
INSERT INTO meeting_rooms (room_name, capacity) VALUES ('Executive Suite', 15);
INSERT INTO meeting_rooms (room_name, capacity) VALUES ('Training Room', 25);
INSERT INTO meeting_rooms (room_name, capacity) VALUES ('Boardroom', 30);

