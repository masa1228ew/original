INSERT IGNORE INTO roles (id, name) VALUES (1, 'ROLE_GENERAL');
INSERT IGNORE INTO users (id, email, password,role_id, enabled) VALUES (1,'taro.samurai@example.com','$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO',1,1);

INSERT IGNORE INTO genre (id, name) VALUES (1, 'ゲーム');
INSERT IGNORE INTO genre (id, name) VALUES (2, '本');
INSERT IGNORE INTO genre (id, name) VALUES (3, '映画');

INSERT IGNORE INTO  collection(id, name,genre_id,user_id,created_at,updated_at) VALUES (1, '積みゲー',1,1,'2023-04-01','2023-04-20');

INSERT IGNORE INTO category(id,category,genre_id)  VALUES (1,'アクション',1);
INSERT IGNORE INTO category(id,category,genre_id)  VALUES (2,'RPG',1);
INSERT IGNORE INTO category(id,category,genre_id)  VALUES (3,'テーブル',1);
INSERT IGNORE INTO category(id,category,genre_id)  VALUES (4,'シミュレーション',1);
INSERT IGNORE INTO category(id,category,genre_id)  VALUES (5,'対戦格闘',1);
INSERT IGNORE INTO category(id,category,genre_id)  VALUES (6,'恋愛',1);
INSERT IGNORE INTO category(id,category,genre_id)  VALUES (7,'テーブル',1);
INSERT IGNORE INTO category(id,category,genre_id)  VALUES (8,'アドベンチャー',1);
INSERT IGNORE INTO category(id,category,genre_id)  VALUES (9,'その他',1);
INSERT IGNORE INTO category(id,category,genre_id)  VALUES (10,'少年漫画',2);
INSERT IGNORE INTO category(id,category,genre_id)  VALUES (11,'青年漫画',2);
INSERT IGNORE INTO category(id,category,genre_id)  VALUES (12,'少女漫画',2);
INSERT IGNORE INTO category(id,category,genre_id)  VALUES (13,'ミステリー',2);
INSERT IGNORE INTO category(id,category,genre_id)  VALUES (14,'SF',2);
INSERT IGNORE INTO category(id,category,genre_id)  VALUES (15,'時代',2);
INSERT IGNORE INTO category(id,category,genre_id)  VALUES (16,'近代小説',2);
INSERT IGNORE INTO category(id,category,genre_id)  VALUES (17,'海外小説',2);
INSERT IGNORE INTO category(id,category,genre_id)  VALUES (18,'ホラー',2);
INSERT IGNORE INTO category(id,category,genre_id)  VALUES (19,'ライトノベル',2);
INSERT IGNORE INTO category(id,category,genre_id)  VALUES (20,'その他',2);
INSERT IGNORE INTO category(id,category,genre_id)  VALUES (21,'青春',3);
INSERT IGNORE INTO category(id,category,genre_id)  VALUES (22,'特撮',3);
INSERT IGNORE INTO category(id,category,genre_id)  VALUES (23,'SF',3);
INSERT IGNORE INTO category(id,category,genre_id)  VALUES (24,'ファンタジー',3);
INSERT IGNORE INTO category(id,category,genre_id)  VALUES (25,'コメディ',3);
INSERT IGNORE INTO category(id,category,genre_id)  VALUES (26,'ドラマ',3);
INSERT IGNORE INTO category(id,category,genre_id)  VALUES (27,'ミステリー',3);
INSERT IGNORE INTO category(id,category,genre_id)  VALUES (28,'ホラー',3);
INSERT IGNORE INTO category(id,category,genre_id)  VALUES (29,'戦争',3);
INSERT IGNORE INTO category(id,category,genre_id)  VALUES (30,'ドキュメンタリー',3);
INSERT IGNORE INTO category(id,category,genre_id)  VALUES (31,'時代劇',3);
INSERT IGNORE INTO category(id,category,genre_id)  VALUES (32,'ヒーロー',3);
INSERT IGNORE INTO category(id,category,genre_id)  VALUES (33,'ミュージカル',3);
INSERT IGNORE INTO category(id,category,genre_id)  VALUES (34,'西部劇',3);
INSERT IGNORE INTO category(id,category,genre_id)  VALUES (35,'アニメ',3);
INSERT IGNORE INTO category(id,category,genre_id)  VALUES (36,'ミュージック',3);
INSERT IGNORE INTO category(id,category,genre_id)  VALUES (37,'その他',3);

INSERT IGNORE INTO datas (id,name,collection_id,category_id,price,created_at,updated_at) VALUES (1,'龍が如く',1,1,290,'2023-04-01','2023-04-20');