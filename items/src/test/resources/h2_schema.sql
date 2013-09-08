DROP TABLE IF EXISTS items;
CREATE TABLE items (
  id  int(11) NOT NULL auto_increment PRIMARY KEY
, name VARCHAR(255) NOT NULL
, state varchar(12) --not null
, created_at DATETIME
, updated_at DATETIME
);

DROP TABLE IF EXISTS timelogs;
create table timelogs(
  id int(11) not null auto_increment primary key
, item_id int(11) not null
, date_from datetime
, date_to datetime
, created_at datetime
, updated_at datetime
);

DROP TABLE IF EXISTS tags;
create table tags(
  id int(11) not null auto_increment primary key
, name varchar(255) not null
, created_at datetime
, updated_at datetime
);

drop table if exists item_tree_paths;
create table item_tree_paths(
  id int(11) not null auto_increment primary key
, ancestor int(11) not null
, descendant int(11) not null
, path_length int(11) not null
, created_at datetime
, updated_at datetime
, unique(ancestor, descendant)
, foreign key (ancestor) REFERENCES items(id)
, foreign key (descendant) REFERENCES items(id)
);