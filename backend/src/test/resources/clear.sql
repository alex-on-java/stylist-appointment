DELETE FROM sa.slot;
DELETE FROM sa.stylist;

SELECT setval('sa.stylist_id_seq', 1, FALSE);