CREATE SCHEMA videotheque AUTHORIZATION SA;

use videotheque;

CREATE TABLE Category (
      id INT AUTO_INCREMENT PRIMARY KEY,
      name VARCHAR(255) NOT NULL UNIQUE
);
CREATE TABLE Film (
      id INT AUTO_INCREMENT PRIMARY KEY,
      title VARCHAR(255) NOT NULL,
      release_year  INT,
      description TEXT,
      release_date DATE,
      duration INT,
      category_id INT,
      FOREIGN KEY (category_id) REFERENCES Category(id)
);
CREATE TABLE Actor (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(255) NOT NULL,
       bio TEXT
);
CREATE TABLE Film_Actor (
        filmId INT,
        actorId INT,
        PRIMARY KEY (filmId, actorId),
        FOREIGN KEY (filmId) REFERENCES Film(id),
        FOREIGN KEY (actorId) REFERENCES Actor(id)
);

