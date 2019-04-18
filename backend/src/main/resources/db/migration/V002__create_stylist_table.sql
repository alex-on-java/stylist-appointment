CREATE TABLE sa.stylist (
  id         SERIAL PRIMARY KEY   NOT NULL,
  name       VARCHAR(255)         NOT NULL,
  email      VARCHAR(255)         NOT NULL,
  UNIQUE (email)
);