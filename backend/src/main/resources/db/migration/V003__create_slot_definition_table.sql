CREATE TABLE sa.slot_definition (
  id         SERIAL PRIMARY KEY   NOT NULL,
  start      TIME                 NOT NULL,
  duration   INT                  NOT NULL
);