CREATE TABLE sa.appointment (
  id                  SERIAL PRIMARY KEY   NOT NULL,
  customer_id         INT                  NOT NULL,
  slot_definition_id  INT                  NOT NULL REFERENCES sa.slot_definition(id),
  stylist_id          INT                  NOT NULL REFERENCES sa.stylist(id),
  "date"              DATE                 NOT NULL,
  UNIQUE (slot_definition_id, stylist_id, "date")
);