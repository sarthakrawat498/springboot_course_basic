--USE `employee_directory`;
--
--DROP TABLE IF EXISTS `authorities`;
--DROP TABLE IF EXISTS `users`;
--
----
---- Table structure for table `users`
----
--
--CREATE TABLE `users` (
--  `username` varchar(50) NOT NULL,
--  `password` varchar(50) NOT NULL,
--  `enabled` tinyint NOT NULL,
--  PRIMARY KEY (`username`)
--) ENGINE=InnoDB DEFAULT CHARSET=latin1;
--
----
---- Inserting data for table `users`
----
--
--INSERT INTO `users`
--VALUES
--('john','{noop}test123',1),
--('mary','{noop}test123',1),
--('susan','{noop}test123',1);
--
--
----
---- Table structure for table `authorities`
----
--
--CREATE TABLE `authorities` (
--  `username` varchar(50) NOT NULL,
--  `authority` varchar(50) NOT NULL,
--  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
--  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
--) ENGINE=InnoDB DEFAULT CHARSET=latin1;
--
----
---- Inserting data for table `authorities`
----
--
--INSERT INTO `authorities`
--VALUES
--('john','ROLE_EMPLOYEE'),
--('mary','ROLE_EMPLOYEE'),
--('mary','ROLE_MANAGER'),
--('susan','ROLE_EMPLOYEE'),
--('susan','ROLE_MANAGER'),
--('susan','ROLE_ADMIN');


-- PostgreSQL does NOT use USE database
-- Make sure you're already connected to employee_directory DB

-- Drop tables (order matters because of FK)
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;

-- ----------------------------
-- Table: users
-- ----------------------------
CREATE TABLE users (
  username VARCHAR(50) PRIMARY KEY,
  password VARCHAR(100) NOT NULL,
  enabled BOOLEAN NOT NULL
);

-- Insert data
INSERT INTO users (username, password, enabled)
VALUES
('john','{noop}test123', true),
('mary','{noop}test123', true),
('susan','{noop}test123', true);

-- ----------------------------
-- Table: authorities
-- ----------------------------
CREATE TABLE authorities (
  username VARCHAR(50) NOT NULL,
  authority VARCHAR(50) NOT NULL,
  CONSTRAINT authorities_pk UNIQUE (username, authority),
  CONSTRAINT authorities_fk FOREIGN KEY (username)
      REFERENCES users(username)
      ON DELETE CASCADE
);

-- Insert data
INSERT INTO authorities (username, authority)
VALUES
('john','ROLE_EMPLOYEE'),
('mary','ROLE_EMPLOYEE'),
('mary','ROLE_MANAGER'),
('susan','ROLE_EMPLOYEE'),
('susan','ROLE_MANAGER'),
('susan','ROLE_ADMIN');