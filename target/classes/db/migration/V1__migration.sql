CREATE TABLE accounts
(id integer NOT NULL AUTO_INCREMENT,
 name varchar(25) NOT NULL,
 password varchar(255) NOT NULL,
 status varchar(55) NOT NULL DEFAULT 'USER',
 PRIMARY KEY (id)
);

CREATE TABLE users
(id integer NOT NULL AUTO_INCREMENT,
 account_id integer NOT NULL,
 PRIMARY KEY (id),
 FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE
);

CREATE TABLE files
(id integer NOT NULL AUTO_INCREMENT,
 name varchar(255) NOT NULL,
 user_id integer NOT NULL,
 PRIMARY KEY (id),
 FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE events
(id integer NOT NULL AUTO_INCREMENT,
 action varchar(255) NOT NULL,
 user_id integer NOT NULL,
 PRIMARY KEY (id),
 FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
