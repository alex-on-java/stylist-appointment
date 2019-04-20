CREATE TABLE sa.slot (
  customer_id         INT    NOT NULL,
  slot_definition_id  INT    NOT NULL REFERENCES sa.slot_definition(id),
  stylist_id          INT    NOT NULL REFERENCES sa.stylist(id),
  "date"              DATE   NOT NULL,
  PRIMARY KEY (slot_definition_id, stylist_id, "date")
);