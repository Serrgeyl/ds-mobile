create user innovo with encrypted password 'innovo';

create database dschool with
    owner innovo
--     LC_COLLATE 'ru_RU.UTF-8'
--     LC_CTYPE 'ru_RU.UTF-8'
    encoding 'UTF8';

grant all privileges on database dschool to innovo;
