CREATE TABLE IF NOT EXISTS dog
(
    id                  BIGSERIAL primary key,
    nickname            varchar not null,
    breed               integer not null,
    age                 integer not null,
    hair_color          integer not null,
    sex                 integer not null
);

-- Таблица называется не user, потому что user - зарезервированное слово

CREATE TABLE IF NOT EXISTS user_table
(
    id                  BIGSERIAL primary key,
    login               varchar not null unique,
    password            varchar not null,
    firstname           varchar not null,
    lastname            varchar not null,
    mail                varchar not null unique,
    phone_number        varchar not null
);

CREATE TABLE IF NOT EXISTS meeting
(
    id                  BIGSERIAL primary key,
    name                varchar not null,
    creation_date       timestamp with time zone not null,
    meeting_date        timestamp with time zone not null,
    author_id              BIGSERIAL not null,
    status              varchar not null,
    CONSTRAINT fk_author
        FOREIGN KEY (author_id)
            REFERENCES dog(id)
);

CREATE TABLE IF NOT EXISTS meeting_dog
(
    meeting_id          BIGSERIAL not null,
    dog_id              BIGSERIAL not null,
    CONSTRAINT fk_meeting
        FOREIGN KEY (meeting_id)
            REFERENCES meeting(id),
    CONSTRAINT fk_dog
        FOREIGN KEY (dog_id)
            REFERENCES dog(id),
    PRIMARY KEY (meeting_id, dog_id)
);

CREATE TABLE IF NOT EXISTS follow
(
    from_id            BIGSERIAL not null,
    to_id            BIGSERIAL not null,
    CONSTRAINT fk_from
        FOREIGN KEY (from_id)
            REFERENCES dog(id),
    CONSTRAINT fk_to
        FOREIGN KEY (to_id)
            REFERENCES dog(id),
    PRIMARY KEY (from_id, to_id)
);


CREATE TABLE IF NOT EXISTS messages
(
    id                  BIGSERIAL primary key,
    dog_from           BIGSERIAL not null,
    dog_to             BIGSERIAL not null,
    content             varchar not null,
    creation_date       DATE not null,
    CONSTRAINT fk_dog_from
        FOREIGN KEY (dog_from)
            REFERENCES dog(id),
    CONSTRAINT fk_dog_to
        FOREIGN KEY (dog_to)
            REFERENCES dog(id)
);