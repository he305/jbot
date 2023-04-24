CREATE TABLE users (
    id uuid NOT NULL,
    name varchar(255),
    audio_source smallint,
    subs_source smallint,
    chat_id VARCHAR(255),
    PRIMARY KEY(id)
);

CREATE TABLE anime_list_info (
    id uuid NOT NULL,
    type smallint,
    nickname VARCHAR(255),
    password VARCHAR(255),
    authorization_code TEXT,
    user_id uuid,
    PRIMARY KEY(id)
);

ALTER TABLE IF EXISTS anime_list_info
    ADD CONSTRAINT FK_anime_list_info_users FOREIGN KEY (user_id) REFERENCES users(id);