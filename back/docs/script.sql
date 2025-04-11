
-- Users
INSERT INTO Userentity (id, username, email, password) VALUES
(1, 'ocrUser1', 'ocruser1@aze.com', '$2a$10$AxtbFQJCu0byMt2idX8O0unQOrepUOhG683gj.hD0xPsisXJl2QOa'),
(2, 'ocrUser2', 'ocruser2@aze.com', '$2a$10$vNSooTf/Xntp2Boo.IvITe9DCuu5wnl9hE7J3s92drlWyEuge9bzm'),
(3, 'ocrUser3', 'ocruser3@aze.com', '$2a$10$KXZsxq/FPO3D9bNDNxi31OAuyerc2GXH2rw.aTHq7qkghjkEj3gTa'),
(4, 'ocrUser4', 'ocruser4@aze.com', '$2a$10$aQAQXFQbLFdDuU3.AdjLAuiFEyWYAndoYKt1ezPeYSrJQbqRYQA6i');
-- (1, 'ocrUser1', 'ocruser1@aze.com', 'ocrMdp1*'),
-- (2, 'ocrUser2', 'ocruser2@aze.com', 'ocrMdp2*'),
-- (3, 'ocrUser3', 'ocruser3@aze.com', 'ocrMdp3*'),
-- (4, 'ocrUser4', 'ocruser4@aze.com', 'ocrMdp4*');

-- Themes
INSERT INTO Theme (id, title, description) VALUES
(1, 'theme1', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.'),
(2, 'theme2', 'Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.'),
(3, 'theme3', 'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris.');

-- for the join table "theme-user"
INSERT INTO theme_user (theme_id, user_id) VALUES
(1, 1),
(2, 1),
(3, 1),
(2, 2),
(3, 2),
(1, 3),
(3, 3);

-- Articles
INSERT INTO Article (id, title, content, created_at, user_id, theme_id) VALUES
(1, 'article1', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', '2025-01-01 12:00:00', 1, 1),
(2, 'article2', 'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.', '2025-01-02 12:00:00', 1, 2),
(3, 'article3', 'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.', '2025-01-03 12:00:00', 2, 1),
(4, 'article4', 'Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', '2025-01-04 12:00:00', 2, 3),
(5, 'article5', 'Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium.', '2025-01-05 12:00:00', 3, 2),
(6, 'article6', 'Totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.', '2025-01-06 12:00:00', 3, 1),
(7, 'article7', 'Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.', '2025-01-07 12:00:00', 1, 3),
(8, 'article8', 'Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.', '2025-01-08 12:00:00', 2, 2),
(9, 'article9', 'Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur?', '2025-01-09 12:00:00', 3, 3),
(10, 'article10', 'Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?', '2025-01-10 12:00:00', 1, 1);


-- Commentaries
INSERT INTO Commentary (id, content, created_at, user_id, article_id) VALUES
(1, 'Lorem ipsum dolor sit amet.', '2025-02-01 12:00:00', 1, 1),
(2, 'Consectetur adipiscing elit.', '2025-02-02 12:00:00', 2, 1),
(3, 'Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', '2025-02-03 12:00:00', 3, 2),
(4, 'Ut enim ad minim veniam.', '2025-02-04 12:00:00', 1, 2),
(5, 'Duis aute irure dolor in reprehenderit.', '2025-02-05 12:00:00', 2, 3),
(6, 'Excepteur sint occaecat cupidatat non proident.', '2025-02-06 12:00:00', 3, 3),
(7, 'Sunt in culpa qui officia deserunt mollit anim id est laborum.', '2025-02-07 12:00:00', 1, 4),
(8, 'Curabitur pretium tincidunt lacus.', '2025-02-08 12:00:00', 2, 4),
(9, 'Nunc ullamcorper, justo eget pulvinar semper.', '2025-02-09 12:00:00', 3, 5),
(10, 'Aenean nonummy hendrerit mauris.', '2025-02-10 12:00:00', 1, 5),
(11, 'Phasellus porta. Fusce suscipit varius mi.', '2025-02-11 12:00:00', 2, 6),
(12, 'Etiam in sem quam. Nulla fermentum.', '2025-02-12 12:00:00', 3, 6),
(13, 'Curabitur massa. Donec eleifend.', '2025-02-13 12:00:00', 1, 7),
(14, 'Maecenas ligula. Nulla interdum.', '2025-02-14 12:00:00', 2, 7),
(15, 'Mauris est. Etiam non diam.', '2025-02-15 12:00:00', 3, 8),
(16, 'Duis facilisis arcu eu massa.', '2025-02-16 12:00:00', 1, 8),
(17, 'Vestibulum varius. Cras tempus.', '2025-02-17 12:00:00', 2, 9),
(18, 'Nam facilisis sollicitudin odio.', '2025-02-18 12:00:00', 3, 9),
(19, 'Fusce ac magna. Quisque massa.', '2025-02-19 12:00:00', 1, 10),
(20, 'Vivamus vulputate libero a auctor.', '2025-02-20 12:00:00', 2, 10);

