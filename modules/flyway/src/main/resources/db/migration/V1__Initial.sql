CREATE TABLE users (
  id          INTEGER primary key NOT NULL,
  castle      VARCHAR,
  guild_id    INTEGER,
  level       INTEGER             NOT NULL DEFAULT 0,
  hero_class  VARCHAR,
  player_name VARCHAR             NOT NULL DEFAULT '',
  last_update TIMESTAMP
);

CREATE TABLE guilds (
  id        SERIAL primary key NOT NULL,
  castle    VARCHAR            NOT NULL,
  name      VARCHAR,
  tag       VARCHAR            NOT NULL,
  commander VARCHAR
);

CREATE TABLE guild_roles (
  user_id INT     NOT NULL,
  role    VARCHAR NOT NULL
);

CREATE TABLE player_items (
  user_id     INT NOT NULL,
  item_id     INT NOT NULL,
  enchantment INT NOT NULL
);
