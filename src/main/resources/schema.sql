CREATE TABLE movie (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(100),
    language VARCHAR(50),
    genre VARCHAR(50),
    duration INT
);

CREATE TABLE theatre (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100),
    city VARCHAR(50),
    address VARCHAR(200)
);

CREATE TABLE shows (
    id BIGSERIAL PRIMARY KEY,
    movie_id BIGINT REFERENCES movie(id),
    theatre_id BIGINT REFERENCES theatre(id),
    show_date DATE,
    show_time TIME,
    price NUMERIC(10,2)
);

CREATE TABLE booking (
    id BIGSERIAL PRIMARY KEY,
    user_name VARCHAR(100),
    show_id BIGINT REFERENCES shows(id),
    total_amount NUMERIC(10,2),
    status VARCHAR(20),
    created_at TIMESTAMP
);

CREATE TABLE booking_seat (
    id BIGSERIAL PRIMARY KEY,
    booking_id BIGINT REFERENCES booking(id),
    show_id BIGINT REFERENCES shows(id),
    seat_number VARCHAR(10),

    CONSTRAINT uk_show_seat
    UNIQUE (show_id, seat_number)
);