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

CREATE TABLE IF NOT EXISTS follow
(
    from_id            BIGSERIAL not null,
    to_id            BIGSERIAL not null,
    PRIMARY KEY (from_id, to_id)

);
