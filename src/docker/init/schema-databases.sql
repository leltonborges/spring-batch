create schema bratch collate utf8_general_ci;
create schema app collate utf8_general_ci;

create user if not exists project
    identified by 'secret123';
grant all privileges on bratch.* to project;
grant all privileges on app.* to project;