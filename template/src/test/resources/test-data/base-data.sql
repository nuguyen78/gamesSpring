-- Insert data into studio table
INSERT INTO studio (name, founded_date, number_of_workers) VALUES
                                                               ('Studio One', '2000-01-01', 150),
                                                               ('Studio Two', '2005-05-15', 80),
                                                               ('Studio Three', '2010-09-20', 200);

-- Insert data into game table
INSERT INTO game (name, release_date, about_the_game, support_url, metacritic_score, studio_id) VALUES
                                                                                                    ('Game One', '2020-10-01', 'An exciting adventure game.', 'http://support.gameone.com', 85, 1),
                                                                                                    ('Game Two', '2018-07-15', 'A thrilling action game.', 'http://support.gametwo.com', 78, 2),
                                                                                                    ('Game Three', '2022-03-20', 'A captivating puzzle game.', 'http://support.gamethree.com', 90, 3);

-- Insert data into review table
INSERT INTO review (text, game_id, created_at) VALUES
                                                   ('Amazing game, highly recommend!', 1, '2020-11-01'),
                                                   ('Good game but needs improvements.', 2, '2018-08-01'),
                                                   ('Excellent game, very challenging.', 3, '2022-04-01');

-- Insert data into category table
INSERT INTO category (name) VALUES
                                ('Action'),
                                ('Adventure'),
                                ('Puzzle'),
                                ('Strategy');

-- Insert data into genre table
INSERT INTO genre (name) VALUES
                              ('RPG'),
                              ('Shooter'),
                              ('Platformer'),
                              ('Simulation');

-- Insert data into game_genre table for the many-to-many relationship
INSERT INTO game_genre (game_id, genre_id) VALUES
                                               (1, 1),
                                               (1, 2),
                                               (2, 2),
                                               (2, 3),
                                               (3, 3),
                                               (3, 4);

-- Insert data into game_category table for the many-to-many relationship
INSERT INTO game_category (game_id, category_id) VALUES
                                                     (1, 1),
                                                     (1, 2),
                                                     (2, 2),
                                                     (2, 3),
                                                     (3, 3),
                                                     (3, 4);
