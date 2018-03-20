# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table friendship (
  id                            integer auto_increment not null,
  friend_requester_id           integer,
  friend_accepter_id            integer,
  date                          varchar(255),
  accepted                      tinyint(1) default 0 not null,
  constraint pk_friendship primary key (id)
);

create table credential (
  id                            integer auto_increment not null,
  user_name                     varchar(255),
  password                      varchar(255),
  constraint uq_credential_user_name unique (user_name),
  constraint pk_credential primary key (id)
);

create table event (
  id                            integer auto_increment not null,
  title                         varchar(255),
  description                   varchar(255),
  due                           varchar(255),
  owner_id                      integer,
  socket_ioroom                 varchar(255),
  constraint pk_event primary key (id)
);

create table task (
  id                            integer auto_increment not null,
  assigned_id                   integer,
  creator_id                    integer,
  title                         varchar(255),
  is_done                       tinyint(1) default 0,
  event_id                      integer,
  constraint pk_task primary key (id)
);

create table user (
  id                            integer auto_increment not null,
  first_name                    varchar(255),
  last_name                     varchar(255),
  email                         varchar(255),
  push_token                    varchar(255),
  credential_id                 integer,
  constraint uq_user_email unique (email),
  constraint uq_user_credential_id unique (credential_id),
  constraint pk_user primary key (id)
);

create table user_event (
  user_id                       integer not null,
  event_id                      integer not null,
  constraint pk_user_event primary key (user_id,event_id)
);

alter table friendship add constraint fk_friendship_friend_requester_id foreign key (friend_requester_id) references user (id) on delete restrict on update restrict;
create index ix_friendship_friend_requester_id on friendship (friend_requester_id);

alter table friendship add constraint fk_friendship_friend_accepter_id foreign key (friend_accepter_id) references user (id) on delete restrict on update restrict;
create index ix_friendship_friend_accepter_id on friendship (friend_accepter_id);

alter table event add constraint fk_event_owner_id foreign key (owner_id) references user (id) on delete restrict on update restrict;
create index ix_event_owner_id on event (owner_id);

alter table task add constraint fk_task_assigned_id foreign key (assigned_id) references user (id) on delete restrict on update restrict;
create index ix_task_assigned_id on task (assigned_id);

alter table task add constraint fk_task_creator_id foreign key (creator_id) references user (id) on delete restrict on update restrict;
create index ix_task_creator_id on task (creator_id);

alter table task add constraint fk_task_event_id foreign key (event_id) references event (id) on delete restrict on update restrict;
create index ix_task_event_id on task (event_id);

alter table user add constraint fk_user_credential_id foreign key (credential_id) references credential (id) on delete restrict on update restrict;

alter table user_event add constraint fk_user_event_user foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_user_event_user on user_event (user_id);

alter table user_event add constraint fk_user_event_event foreign key (event_id) references event (id) on delete restrict on update restrict;
create index ix_user_event_event on user_event (event_id);


# --- !Downs

alter table friendship drop foreign key fk_friendship_friend_requester_id;
drop index ix_friendship_friend_requester_id on friendship;

alter table friendship drop foreign key fk_friendship_friend_accepter_id;
drop index ix_friendship_friend_accepter_id on friendship;

alter table event drop foreign key fk_event_owner_id;
drop index ix_event_owner_id on event;

alter table task drop foreign key fk_task_assigned_id;
drop index ix_task_assigned_id on task;

alter table task drop foreign key fk_task_creator_id;
drop index ix_task_creator_id on task;

alter table task drop foreign key fk_task_event_id;
drop index ix_task_event_id on task;

alter table user drop foreign key fk_user_credential_id;

alter table user_event drop foreign key fk_user_event_user;
drop index ix_user_event_user on user_event;

alter table user_event drop foreign key fk_user_event_event;
drop index ix_user_event_event on user_event;

drop table if exists friendship;

drop table if exists credential;

drop table if exists event;

drop table if exists task;

drop table if exists user;

drop table if exists user_event;

