-- Insert data into studio table
INSERT INTO studio (id, name, founded_date, number_of_workers) VALUES
                                                                   (1, 'Rockstar Studios', '2004-01-01', 300),
                                                                   (2, 'Naughty Dog', '1984-09-01', 500),
                                                                   (3, 'CD Projekt Red', '1994-01-01', 800),
                                                                   (4, 'Ubisoft Montreal', '1997-01-01', 1200),
                                                                   (5, 'Bethesda Game Studios', '2001-01-01', 600),
                                                                   (6, 'Capcom', '1979-06-11', 2000),
                                                                   (7, 'Square Enix', '1986-09-22', 3500),
                                                                   (8, 'Nintendo', '1889-09-23', 6000),
                                                                   (9, 'Bungie', '1991-05-16', 700),
                                                                   (10, 'Epic Games', '1991-08-06', 2500);

-- Insert data into game table
INSERT INTO game (id, name, release_date, about_the_game, support_url, metacritic_score, studio_id) VALUES
                                                                                                        -- Rocksteady Studios games
                                                                                                        (1, 'Batman: Arkham Knight', '2015-06-23', 'The explosive finale to the Arkham series.', 'http://support.batmanarkhamknight.com', 87, 1),
                                                                                                        (2, 'Batman: Arkham City', '2011-10-18', 'Gotham City awaits you, the Dark Knight.', 'http://support.batmanarkhamcity.com', 91, 1),
                                                                                                        (3, 'Batman: Arkham Asylum', '2009-08-25', 'The original adventure of the Dark Knight.', 'http://support.batmanarkhamasylum.com', 91, 1),
                                                                                                        -- Naughty Dog games
                                                                                                        (4, 'The Last of Us Part II', '2020-06-19', 'An emotional journey through a post-apocalyptic world.', 'http://support.thelastofus2.com', 93, 2),
                                                                                                        (5, 'Uncharted 4: A Thief''s End', '2016-05-10', 'Join Nathan Drake on his greatest adventure yet.', 'http://support.uncharted4.com', 93, 2),
                                                                                                        (6, 'The Last of Us', '2013-06-14', 'Survival horror action-adventure set in a post-apocalyptic world.', 'http://support.thelastofus.com', 95, 2),
                                                                                                        -- CD Projekt Red games
                                                                                                        (7, 'Cyberpunk 2077', '2020-12-10', 'An open-world RPG set in the dystopian Night City.', 'http://support.cyberpunk2077.com', 70, 3),
                                                                                                        (8, 'The Witcher 3: Wild Hunt', '2015-05-19', 'Open-world RPG with a deep narrative and monster hunting.', 'http://support.thewitcher3.com', 93, 3),
                                                                                                        (9, 'Cyberpunk 2020', '2012-01-01', 'A classic tabletop RPG set in a cyberpunk world.', 'http://support.cyberpunk2020.com', 88, 3),
                                                                                                        -- Ubisoft Montreal games
                                                                                                        (10, 'Assassin''s Creed Valhalla', '2020-11-10', 'Lead epic Viking raids against Saxon troops and fortresses.', 'http://support.assassinscreedvalhalla.com', 83, 4),
                                                                                                        (11, 'Far Cry 5', '2018-03-27', 'Welcome to Hope County, Montana, land of the free and the brave.', 'http://support.farcry5.com', 78, 4),
                                                                                                        (12, 'Watch Dogs: Legion', '2020-10-29', 'Recruit resistance fighters and liberate London from a high-tech surveillance state.', 'http://support.watchdogslegion.com', 77, 4),
                                                                                                        -- Bethesda Game Studios games
                                                                                                        (13, 'The Elder Scrolls V: Skyrim', '2011-11-11', 'Open-world action RPG with epic dragons and sprawling landscapes.', 'http://support.skyrim.com', 94, 5),
                                                                                                        (14, 'Fallout 4', '2015-11-10', 'Explore the wasteland and rebuild civilization.', 'http://support.fallout4.com', 88, 5),
                                                                                                        (15, 'DOOM Eternal', '2020-03-20', 'Rip and tear through the armies of Hell in an epic single-player campaign.', 'http://support.doometernal.com', 88, 5),
                                                                                                        -- Additional games
                                                                                                        (16, 'Red Dead Redemption 2', '2018-10-26', 'An epic tale of life in America at the dawn of the modern age.', 'http://support.reddeadredemption2.com', 97, 4),
                                                                                                        (17, 'God of War', '2018-04-20', 'Kratos returns with his son Atreus in a journey through Norse mythology.', 'http://support.godofwar.com', 94, 6),
                                                                                                        (18, 'Horizon Zero Dawn', '2017-02-28', 'Explore a lush world inhabited by mysterious mechanized creatures.', 'http://support.horizonzerodawn.com', 89, 6),
                                                                                                        (19, 'Grand Theft Auto V', '2013-09-17', 'Explore the open-world of Los Santos and Blaine County in GTA V.', 'http://support.grandtheftauto5.com', 97, 4),
                                                                                                        (20, 'Minecraft', '2011-11-18', 'Build, explore, and survive in the sandbox world of Minecraft.', 'http://support.minecraft.com', 93, 7),
                                                                                                        -- more games ...
                                                                                                        (21, 'The Legend of Zelda: Breath of the Wild', '2017-03-03', 'Embark on a journey through the wilds of Hyrule.', 'http://support.zelda.com', 97, 8),
                                                                                                        (22, 'Super Mario Odyssey', '2017-10-27', 'Join Mario on a massive, globe-trotting 3D adventure!', 'http://support.marioodyssey.com', 97, 9),
                                                                                                        -- and more games ...
                                                                                                        (49, 'Destiny 2', '2017-09-06', 'Become a Guardian of the last safe city on Earth.', 'http://support.destiny2.com', 86, 11),
                                                                                                        (50, 'Halo Infinite', '2021-12-08', 'Return to the Halo universe in a new chapter of Master Chief''s epic journey.', 'http://support.haloinfinite.com', 86, 10);

-- Insert data into review table
INSERT INTO review (id, text, game_id, created_at) VALUES
                                                       (1, 'Amazing game, highly recommend!', 1, '2015-07-01'),
                                                       (2, 'A masterpiece in storytelling and gameplay.', 2, '2020-06-25'),
                                                       (3, 'Ambitious but flawed, with potential for improvement.', 3, '2020-12-15'),
                                                       (4, 'A brilliant RPG with endless possibilities.', 8, '2015-05-20'),
                                                       (5, 'Absolutely stunning visuals and engaging story.', 13, '2011-11-12'),
                                                       (6, 'Incredible world-building and immersive experience.', 16, '2018-10-27'),
                                                       (7, 'An unforgettable journey with deep emotional resonance.', 17, '2018-04-21'),
                                                       (8, 'A groundbreaking open-world game with rich lore.', 18, '2017-03-01'),
                                                       (9, 'One of the best open-world games ever made.', 19, '2013-09-18'),
                                                       (10, 'Revolutionized the survival genre with its creativity.', 20, '2011-11-19'),
                                                       -- more reviews ...
                                                       (45, 'Stunning graphics and epic multiplayer battles.', 49, '2017-09-07'),
                                                       (46, 'Master Chief is back and better than ever!', 50, '2021-12-09');

-- Insert data into category table
INSERT INTO category (id, name) VALUES
                                    (1, 'Action'),
                                    (2, 'Adventure'),
                                    (3, 'RPG'),
                                    (4, 'Open World'),
                                    (5, 'Survival'),
                                    (6, 'Shooter'),
                                    (7, 'Platformer'),
                                    (8, 'Simulation'),
                                    (9, 'Strategy'),
                                    (10, 'Sports');

-- Insert data into genre table
INSERT INTO genre (id, name) VALUES
                                 (1, 'Action-Adventure'),
                                 (2, 'Stealth'),
                                 (3, 'Survival Horror'),
                                 (4, 'Science Fiction'),
                                 (5, 'Fantasy'),
                                 (6, 'First-Person Shooter'),
                                 (7, 'Third-Person Shooter'),
                                 (8, 'Open World'),
                                 (9, 'Racing'),
                                 (10, 'Puzzle'),
                                 (11, 'Simulation'),
                                 (12, 'MMORPG');

-- Insert data into game_genre table for the many-to-many relationship
INSERT INTO game_genre (game_id, genre_id) VALUES
                                               -- Batman: Arkham Knight
                                               (1, 1), (1, 2), (1, 8),
                                               -- The Last of Us Part II
                                               (4, 1), (4, 3),
                                               -- Cyberpunk 2077
                                               (7, 3), (7, 4),
                                               -- Assassin's Creed Valhalla
                                               (10, 1), (10, 8),
                                               -- Far Cry 5
                                               (11, 1), (11, 6),
                                               -- Watch Dogs: Legion
                                               (12, 1), (12, 8),
                                               -- The Elder Scrolls V: Skyrim
                                               (13, 3), (13, 4), (13, 8),
                                               -- Fallout 4
                                               (14, 3), (14, 4), (14, 8),
                                               -- DOOM Eternal
                                               (15, 6),
                                               -- Red Dead Redemption 2
                                               (16, 1), (16, 8),
                                               -- God of War
                                               (17, 1), (17, 5),
                                               -- Horizon Zero Dawn
                                               (18, 1), (18, 4), (18, 8),
                                               -- Grand Theft Auto V
                                               (19, 1), (19, 8),
                                               -- Minecraft
                                               (20, 7), (20, 10),
                                               -- More games ...
                                               (21, 1), (21, 8), (21, 5),
                                               (22, 7), (22, 10),
                                               -- and more games ...
                                               (49, 6), (49, 12),
                                               (50, 6);

-- Insert data into game_category table for the many-to-many relationship
INSERT INTO game_category (game_id, category_id) VALUES
                                                     -- Batman: Arkham Knight
                                                     (1, 1), (1, 2), (1, 4),
                                                     -- The Last of Us Part II
                                                     (4, 1), (4, 2), (4, 5),
                                                     -- Cyberpunk 2077
                                                     (7, 1), (7, 4), (7, 8),
                                                     -- Assassin's Creed Valhalla
                                                     (10, 1), (10, 4), (10, 9),
                                                     -- Far Cry 5
                                                     (11, 1), (11, 4), (11, 6),
                                                     -- Watch Dogs: Legion
                                                     (12, 1), (12, 4), (12, 8),
                                                     -- The Elder Scrolls V: Skyrim
                                                     (13, 1), (13, 3), (13, 4), (13, 8),
                                                     -- Fallout 4
                                                     (14, 1), (14, 3), (14, 4), (14, 8),
                                                     -- DOOM Eternal
                                                     (15, 1), (15, 6),
                                                     -- Red Dead Redemption 2
                                                     (16, 1), (16, 4), (16, 8),
                                                     -- God of War
                                                     (17, 1), (17, 5),
                                                     -- Horizon Zero Dawn
                                                     (18, 1), (18, 4), (18, 8),
                                                     -- Grand Theft Auto V
                                                     (19, 1), (19, 4), (19, 6),
                                                     -- Minecraft
                                                     (20, 7), (20, 10),
                                                     -- More games ...
                                                     (21, 1), (21, 8), (21, 5),
                                                     (22, 7), (22, 10),
                                                     -- and more games ...
                                                     (49, 6), (49, 12),
                                                     (50, 6);

-- For PostgreSQL
SELECT setval(pg_get_serial_sequence('studio', 'id'), 11, true);
SELECT setval(pg_get_serial_sequence('game', 'id'), 51, true);
SELECT setval(pg_get_serial_sequence('review', 'id'), 47, true);
SELECT setval(pg_get_serial_sequence('category', 'id'), 11, true);
SELECT setval(pg_get_serial_sequence('genre', 'id'), 13, true);
