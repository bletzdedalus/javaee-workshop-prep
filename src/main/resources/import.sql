-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
insert into Animal (id, name, type, comment, available) values(nextval('hibernate_sequence'), 'Chloe', "Fish", "boring", "true");
insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-2');
insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-3');