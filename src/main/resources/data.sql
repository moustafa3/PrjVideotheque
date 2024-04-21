use videotheque;

INSERT INTO Category (name) VALUES ('Drame'), ('Comédie'), ('Science-Fiction');

INSERT INTO Film (title, release_year, description, release_date, duration, category_id)
VALUES ('Inception', 2010, 'Un voleur utilise la technologie du rêve pour infiltrer l''esprit', '2010-07-16', 148, 3),
       ('Interstellar', 2014, 'Une équipe d''explorateurs voyage à travers un trou de ver dans l''espace', '2014-11-07', 169, 3),
       ('Le Dîner de Cons', 1998, 'Tous les mercredis, un groupe d''amis organise un dîner de cons', '1998-04-15', 80, 2);

INSERT INTO Actor (name, bio) VALUES
      ('Leonardo DiCaprio', 'Né en 1974, Leonardo DiCaprio est un acteur américain célèbre.'),
      ('Matthew McConaughey', 'Né en 1969, Matthew McConaughey est un acteur américain reconnu pour ses rôles variés.'),
      ('Thierry Lhermitte', 'Acteur français, né en 1952, connu pour ses nombreux rôles dans des comédies.');

INSERT INTO Film_Actor (filmId, actorId) VALUES
      (1, 1), (2, 2), (3, 3);
