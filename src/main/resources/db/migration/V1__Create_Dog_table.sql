CREATE TABLE IF NOT EXISTS dog
(
    id                  BIGSERIAL primary key,
    nickname            varchar not null,
    breed               integer not null,
    age                 integer not null,
    hair_color          integer not null
);

-- Таблица называется не user, потому что user - зарезервированное слово

CREATE TABLE IF NOT EXISTS user_table
(
    id                  BIGSERIAL primary key,
    login               varchar not null,
    password            varchar not null,
    firstname           varchar not null,
    lastname            varchar not null,
    mail                varchar not null,
    phone_number        varchar not null
);

CREATE TABLE IF NOT EXISTS meeting
(
    id                  BIGSERIAL primary key,
    name                varchar not null,
    creation_date       date not null,
    meeting_date        date not null,
    status              varchar not null
);

CREATE TABLE IF NOT EXISTS meeting_dog
(
    meeting_id          BIGSERIAL not null,
    dog_id              BIGSERIAL not null,
    PRIMARY KEY (meeting_id, dog_id)
);