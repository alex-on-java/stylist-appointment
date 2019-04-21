INSERT INTO sa.stylist ("name", email) VALUES
('Stylist 1', 'stylist.1@style.com'),
('Stylist 2', 'stylist.2@style.com'),
('Stylist 3', 'stylist.3@style.com')
;

INSERT INTO sa.appointment (customer_id, slot_definition_id, stylist_id, "date") VALUES
(0, 1, 1, '2010-10-10'),
(0, 1, 2, '2010-10-10'),
(0, 1, 3, '2010-10-10'),
(0, 2, 1, '2010-10-10'),
(0, 2, 3, '2010-10-10'),
(0, 3, 1, '2010-10-10'),
(0, 4, 2, '2010-10-10'),
(0, 1, 3, '2010-10-11')
;
