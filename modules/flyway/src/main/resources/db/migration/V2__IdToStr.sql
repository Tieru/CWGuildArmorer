DROP TABLE player_items;

CREATE TABLE player_items (
  user_id     INT NOT NULL,
  item_id     VARCHAR NOT NULL,
  enchantment INT NOT NULL
);
