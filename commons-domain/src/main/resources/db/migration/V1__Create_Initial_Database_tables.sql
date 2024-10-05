CREATE SCHEMA IF NOT EXISTS HOTEL_TCC;
SET search_path TO HOTEL_TCC;

CREATE SEQUENCE if not exists hotel_seq
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1
    NO MAXVALUE;

CREATE SEQUENCE if not exists room_seq
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1
    NO MAXVALUE;

CREATE SEQUENCE if not exists reserve_seq
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1
    NO MAXVALUE;

CREATE SEQUENCE if not exists room_occupation_seq
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1
    NO MAXVALUE;

CREATE SEQUENCE if not exists hotel_room_seq
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1
    NO MAXVALUE;

CREATE SEQUENCE if not exists user_seq
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1
    NO MAXVALUE;

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
    room_occupation_begin TIMESTAMP NOT NULL,
    room_occupation_end TIMESTAMP NOT NULL,
    occupation VARCHAR(50) NOT NULL,
    constraint pk_room_occupation primary key (id),
    constraint fk_room_occupation_hotel_room foreign key (hotel_room_id) references hotel_room(id)
);

CREATE TABLE IF NOT EXISTS HOTEL_TCC.user(
    id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    constraint pk_customer primary key (id)
);

CREATE TABLE IF NOT EXISTS HOTEL_TCC.reserve(
    id BIGINT NOT NULL,
    customer_id BIGINT NOT NULL,
    reserve_begin TIMESTAMP NOT NULL,
    reserve_end TIMESTAMP NOT NULL,
    hotel_room_id BIGINT NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    constraint pk_reserve primary key (id),
    constraint fk_reserve_hotel_room foreign key (hotel_room_id) references hotel_room(id)
);

CREATE OR REPLACE FUNCTION update_timestamp()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER set_timestamp
    BEFORE INSERT OR UPDATE ON HOTEL_TCC.reserve
    FOR EACH ROW
EXECUTE FUNCTION update_timestamp();

--SPRING SECURITY

create table users(username varchar(50) not null primary key,password varchar(500) not null,enabled boolean not null);
create table authorities (username varchar(50) not null,authority varchar(50) not null,constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on authorities (username,authority);

