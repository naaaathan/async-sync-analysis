INSERT INTO hotel_tcc.customer
    (id, customer_name)
VALUES
    (nextval('hotel_tcc.customer_seq'), 'John Doe') on conflict do nothing;

INSERT INTO hotel_tcc.hotel
    (id, hotel_name, hotel_address)
VALUES
    (nextval('hotel_tcc.hotel_seq'), 'Hotel A', '123 Main St') on conflict do nothing;;

insert into hotel_tcc.room
    (id, room_name, room_description, room_capacity, price_per_day)
VALUES
    (nextval('hotel_tcc.room_seq'), 'Room 1', 'Room 1 description', 2, 100.00) on conflict do nothing;;

insert into hotel_tcc.hotel_room
    (id, hotel_id, room_id)
VALUES
    (nextval('hotel_tcc.hotel_room_seq'), 1, 1) on conflict do nothing;


-- INSERT INTO hotel_tcc.room_occupation
-- (id, hotel_room_id, room_occupation_begin, room_occupation_end,occupation)
-- VALUES
--     (nextval('hotel_tcc.room_occupation_seq'), 1, '2024-05-10', '2024-05-10','UNOCCUPIED') on conflict do nothing;
--
--
-- INSERT INTO hotel_tcc.room_occupation
-- (id, hotel_room_id, room_occupation_begin, room_occupation_end,occupation)
-- VALUES
--     (nextval('hotel_tcc.room_occupation_seq'), 1, '2024-05-11', '2024-05-11','UNOCCUPIED') on conflict do nothing;
--
-- INSERT INTO hotel_tcc.room_occupation
-- (id, hotel_room_id, room_occupation_begin, room_occupation_end,occupation)
-- VALUES
--     (nextval('hotel_tcc.room_occupation_seq'), 1, '2024-05-12', '2024-05-12','UNOCCUPIED') on conflict do nothing;
--
-- INSERT INTO hotel_tcc.room_occupation
--     (id, hotel_room_id, room_occupation_begin, room_occupation_end,occupation)
-- VALUES
--     (nextval('hotel_tcc.room_occupation_seq'), 1, '2024-05-13', '2024-05-14','OCCUPIED') on conflict do nothing;