CREATE DATABASE dao_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

use dao_db;

CREATE TABLE users(
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email varchar(255) NOT NULL UNIQUE,
    username varchar(255) NOT NULL,
    password varchar(60) NOT NULL
);

INSERT INTO users(username, email, password) VALUES ('Maniek', 'maniek@lgchem.com', 'haslo');
select *
from users where id=2;




