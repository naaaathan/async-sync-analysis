INSERT INTO hotel_tcc.user
    (id, name, email, role)
VALUES (nextval('hotel_tcc.user_seq'), 'Nathan Augusto', 'nathan.caugusto@gmail.com', 'MANAGER'),
       (nextval('hotel_tcc.user_seq'), 'Alice Johnson', 'alice.johnson@example.com', 'CUSTOMER'),
       (nextval('hotel_tcc.user_seq'), 'Bob Smith', 'bob.smith@example.com', 'CUSTOMER'),
       (nextval('hotel_tcc.user_seq'), 'Charlie Brown', 'charlie.brown@example.com', 'CUSTOMER'),
       (nextval('hotel_tcc.user_seq'), 'Diana Prince', 'diana.prince@example.com', 'CUSTOMER'),
       (nextval('hotel_tcc.user_seq'), 'Eve Adams', 'eve.adams@example.com', 'CUSTOMER');

INSERT INTO hotel_tcc.hotel
    (id, hotel_name, hotel_address)
VALUES (nextval('hotel_tcc.hotel_seq'), 'Hotel A', '123 Main St'),
       (nextval('hotel_tcc.hotel_seq'), 'Hotel B', '456 Elm St'),
       (nextval('hotel_tcc.hotel_seq'), 'Hotel C', '789 Oak St');

insert into hotel_tcc.room
    (id, room_name, room_description, room_capacity, price_per_day)
VALUES (nextval('hotel_tcc.room_seq'), 'Room AQ', 'Hotel A Room AQ description', 2, 100.00),
       (nextval('hotel_tcc.room_seq'), 'Room PO', 'Hotel B Room PO description', 3, 150.00),
       (nextval('hotel_tcc.room_seq'), 'Room LK', 'Hotel B Room LK description', 4, 200.00),
       (nextval('hotel_tcc.room_seq'), 'Room MJ', 'Hotel C Room MJ description', 2, 120.00),
       (nextval('hotel_tcc.room_seq'), 'Room OT', 'Hotel C Room OT description', 1, 80.00);

insert into hotel_tcc.hotel_room
    (id, hotel_id, room_id)
VALUES (nextval('hotel_tcc.hotel_room_seq'), 1, 1),
       (nextval('hotel_tcc.hotel_room_seq'), 2, 2),
       (nextval('hotel_tcc.hotel_room_seq'), 2, 3),
       (nextval('hotel_tcc.hotel_room_seq'), 3, 4),
       (nextval('hotel_tcc.hotel_room_seq'), 3, 5);


-- INSERT INTO hotel_tcc.room_occupation
-- (id, hotel_room_id, room_occupation_begin, room_occupation_end,occupation)
-- VALUES
--     (nextval('hotel_tcc.room_occupation_seq'), 1, '2024-05-10', '2024-05-10','UNOCCUPIED') ;
--
--
-- INSERT INTO hotel_tcc.room_occupation
-- (id, hotel_room_id, room_occupation_begin, room_occupation_end,occupation)
-- VALUES
--     (nextval('hotel_tcc.room_occupation_seq'), 1, '2024-05-11', '2024-05-11','UNOCCUPIED') ;
--
-- INSERT INTO hotel_tcc.room_occupation
-- (id, hotel_room_id, room_occupation_begin, room_occupation_end,occupation)
-- VALUES
--     (nextval('hotel_tcc.room_occupation_seq'), 1, '2024-05-12', '2024-05-12','UNOCCUPIED') ;
--
-- INSERT INTO hotel_tcc.room_occupation
--     (id, hotel_room_id, room_occupation_begin, room_occupation_end,occupation)
-- VALUES
--     (nextval('hotel_tcc.room_occupation_seq'), 1, '2024-05-13', '2024-05-14','OCCUPIED') ;