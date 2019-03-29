CREATE TABLE users (
  id          BIGINT primary key NOT NULL,
  guild_id    BIGINT,
  level       INTEGER            NOT NULL DEFAULT 0,
  hero_class  VARCHAR,
  player_name VARCHAR            NOT NULL DEFAULT '',
  last_update TIMESTAMP          NOT NULL
);

CREATE TABLE guilds (
  id   BIGSERIAL primary key NOT NULL,
  name VARCHAR               NOT NULL,
  tag  VARCHAR               NOT NULL
);

CREATE TABLE guild_roles (
  user_id BIGINT  NOT NULL,
  role    VARCHAR NOT NULL
);

CREATE TABLE player_items (
  user_id     BIGINT NOT NULL,
  item_id     INT    NOT NULL,
  enchantment INT    NOT NULL
);
