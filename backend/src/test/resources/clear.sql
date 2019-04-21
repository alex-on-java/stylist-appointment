DELETE FROM sa.appointment;
DELETE FROM sa.stylist;

SELECT setval('sa.stylist_id_seq', 1, FALSE);
SELECT setval('sa.appointment_id_seq', 1, FALSE);