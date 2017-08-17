/* User */
create table if not exists `users` (
  `id` character varying(36) primary key,
  `username` character varying(256),
  `prefs` character varying(4096) not null,
  `email` character varying(1024) not null,
  `role` character varying(64) not null,
  `created` timestamp not null,
  unique key `users_email_idx` (`email`),
  unique key `users_username_idx` (`username`)
);

create table if not exists `password_info` (
  `provider` varchar(64) not null,
  `key` varchar(2048) not null,
  `hasher` varchar(64) not null,
  `password` varchar(256) not null,
  `salt` varchar(256),
  `created` timestamp not null,
  primary key (`provider`, `key`)
);

/* System Settings */
create table if not exists `setting_values` (
  `k` character varying(256) primary key,
  `v` character varying(4096) not null
);
