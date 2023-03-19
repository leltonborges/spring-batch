create schema batch collate utf8_general_ci;
create schema project collate utf8_general_ci;

create user if not exists project
    identified by 'secret123';
grant all privileges on batch.* to project;
grant all privileges on project.* to project;