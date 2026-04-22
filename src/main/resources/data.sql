INSERT INTO movie (id, title, language, genre, duration) VALUES
(1, 'KGF Chapter 2', 'Kannada', 'Action', 168),
(2, 'Jawan', 'Hindi', 'Action', 170),
(3, 'Interstellar', 'English', 'Sci-Fi', 169),
(4, 'Kantara', 'Kannada', 'Drama', 148);

INSERT INTO theatre (id, name, city, address) VALUES
(1, 'PVR Phoenix Mall', 'Bangalore', 'Whitefield'),
(2, 'INOX Forum Mall', 'Bangalore', 'Koramangala'),
(3, 'Cinepolis Orion Mall', 'Bangalore', 'Rajajinagar'),
(4, 'Miraj Cinemas', 'Mysore', 'City Center');

INSERT INTO shows (id, movie_id, theatre_id, show_date, show_time, price) VALUES
(1, 1, 1, '2026-04-22', '10:00:00', 250.00),
(2, 1, 2, '2026-04-22', '14:30:00', 300.00),
(3, 2, 1, '2026-04-22', '18:00:00', 280.00),
(4, 3, 3, '2026-04-22', '21:15:00', 350.00),
(5, 4, 4, '2026-04-22', '16:00:00', 220.00);

INSERT INTO booking (id, user_name, show_id, total_amount, status, created_at) VALUES
(1, 'Rahul', 1, 500.00, 'BOOKED', NOW()),
(2, 'Priya', 3, 280.00, 'BOOKED', NOW());

INSERT INTO booking_seat (id, booking_id, show_id, seat_number) VALUES
(1, 1, 1, 'A1'),
(2, 1, 1, 'A2'),
(3, 2, 3, 'B1');