
-- Users
INSERT INTO User (id, username, email, password) VALUES
(1, 'user1', 'user1@aze.com', 'password1'),
(2, 'user2', 'user2@aze.com', 'password2'),
(3, 'user3', 'user3@aze.com', 'password3');

-- Themes
INSERT INTO Theme (id, title, description, user_id) VALUES
(1, 'theme1', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 1),
(2, 'theme2', 'Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 2),
(3, 'theme3', 'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris.', 3);

-- Articles
INSERT INTO Article (id, title, created_at, user_id, theme_id) VALUES
(1, 'article1', '2025-01-01 12:00:00', 1, 1),
(2, 'article2', '2025-01-02 12:00:00', 1, 2),
(3, 'article3', '2025-01-03 12:00:00', 2, 1),
(4, 'article4', '2025-01-04 12:00:00', 2, 3),
(5, 'article5', '2025-01-05 12:00:00', 3, 2),
(6, 'article6', '2025-01-06 12:00:00', 3, 1),
(7, 'article7', '2025-01-07 12:00:00', 1, 3),
(8, 'article8', '2025-01-08 12:00:00', 2, 2),
(9, 'article9', '2025-01-09 12:00:00', 3, 3),
(10, 'article10', '2025-01-10 12:00:00', 1, 1);

-- Commentaries
INSERT INTO Commentary (id, content, user_id, article_id) VALUES
(1, 'Lorem ipsum dolor sit amet.', 1, 1),
(2, 'Consectetur adipiscing elit.', 2, 1),
(3, 'Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 3, 2),
(4, 'Ut enim ad minim veniam.', 1, 2),
(5, 'Duis aute irure dolor in reprehenderit.', 2, 3),
(6, 'Excepteur sint occaecat cupidatat non proident.', 3, 3),
(7, 'Sunt in culpa qui officia deserunt mollit anim id est laborum.', 1, 4),
(8, 'Curabitur pretium tincidunt lacus.', 2, 4),
(9, 'Nunc ullamcorper, justo eget pulvinar semper.', 3, 5),
(10, 'Aenean nonummy hendrerit mauris.', 1, 5),
(11, 'Phasellus porta. Fusce suscipit varius mi.', 2, 6),
(12, 'Etiam in sem quam. Nulla fermentum.', 3, 6),
(13, 'Curabitur massa. Donec eleifend.', 1, 7),
(14, 'Maecenas ligula. Nulla interdum.', 2, 7),
(15, 'Mauris est. Etiam non diam.', 3, 8),
(16, 'Duis facilisis arcu eu massa.', 1, 8),
(17, 'Vestibulum varius. Cras tempus.', 2, 9),
(18, 'Nam facilisis sollicitudin odio.', 3, 9),
(19, 'Fusce ac magna. Quisque massa.', 1, 10),
(20, 'Vivamus vulputate libero a auctor.', 2, 10);
