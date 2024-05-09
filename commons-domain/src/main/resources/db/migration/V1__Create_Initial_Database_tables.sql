CREATE SCHEMA IF NOT EXISTS HOTEL_TCC;
SET search_path TO HOTEL_TCC;

CREATE TABLE IF NOT EXISTS HOTEL_TCC.hotel (
    id BIGINT NOT NULL,
    hotel_name VARCHAR(255) NOT NULL,
    hotel_address VARCHAR(255) NOT NULL,
    constraint pk_hotel primary key (id)
);

CREATE TABLE IF NOT EXISTS HOTEL_TCC.room (
    id BIGINT NOT NULL,
    room_name VARCHAR(255) NOT NULL,
    room_description VARCHAR(255) NOT NULL,
    room_capacity int NOT NULL,
    price_per_day DECIMAL(10,2) NOT NULL,
    constraint pk_room primary key (id)
);

CREATE TABLE IF NOT EXISTS HOTEL_TCC.hotel_room (
    id BIGINT NOT NULL,
    hotel_id BIGINT NOT NULL,
    room_id BIGINT NOT NULL,
    constraint pk_hotel_room primary key (id),
    constraint fk_hotel_room_hotel foreign key (hotel_id) references hotel(id),
    constraint fk_hotel_room_room foreign key (room_id) references room(id)
);

CREATE TABLE IF NOT EXISTS HOTEL_TCC.room_occupation(
    id BIGINT NOT NULL,
    hotel_room_id BIGINT NOT NULL,
    room_occupation_begin DATE NOT NULL,
    occupied VARCHAR(50) NOT NULL,
    constraint pk_room_occupation primary key (id),
    constraint fk_room_occupation_hotel_room foreign key (hotel_room_id) references hotel_room(id)
);

CREATE TABLE IF NOT EXISTS HOTEL_TCC.customer(
    id BIGINT NOT NULL,
    customer_name VARCHAR(255) NOT NULL,
    constraint pk_customer primary key (id)
);

CREATE TABLE IF NOT EXISTS HOTEL_TCC.reserve(
    id BIGINT NOT NULL,
    customer_id BIGINT NOT NULL,
    reserve_begin DATE NOT NULL,
    reserve_end DATE NOT NULL,
    hotel_room_id BIGINT NOT NULL,
    status VARCHAR(50) NOT NULL,
    constraint pk_reserve primary key (id),
    constraint fk_reserve_hotel_room foreign key (hotel_room_id) references hotel_room(id)
);

