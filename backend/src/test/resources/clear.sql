DELETE FROM sa.stylist;
DELETE FROM sa.slot;

SELECT setval('sa.stylist_id_seq', 1, FALSE);