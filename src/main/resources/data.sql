CREATE TABLE IF NOT EXISTS CATEGORY
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(255),
    parent_idx INT,
    child_id INT
);

INSERT INTO category (name, parent_idx, child_id)
VALUES ('남자', null, 1);
INSERT INTO category (name, parent_idx, child_id)
VALUES ('여자', null, 2);

INSERT INTO category (name, parent_idx, child_id)
VALUES ('엑소', 1, 3);
INSERT INTO category (name, parent_idx, child_id)
VALUES ('방탄소년단', 1, 4);
INSERT INTO category (name, parent_idx, child_id)
VALUES ('블랙핑크', 2, 5);

INSERT INTO category (name, parent_idx, child_id)
VALUES ('공지사항', 3, 6);
INSERT INTO category (name, parent_idx, child_id)
VALUES ('첸', 3, 7);
INSERT INTO category (name, parent_idx, child_id)
VALUES ('백현', 3, 8);
INSERT INTO category (name, parent_idx, child_id)
VALUES ('시우민', 3, 9);

INSERT INTO category (name, parent_idx, child_id)
VALUES ('공지사항', 4, 10);
INSERT INTO category (name, parent_idx, child_id)
VALUES ('익명게시판', 4, 11);
INSERT INTO category (name, parent_idx, child_id)
VALUES ('뷔', 4, 12);

INSERT INTO category (name, parent_idx, child_id)
VALUES ('공지사항', 5, 13);
INSERT INTO category (name, parent_idx, child_id)
VALUES ('익명게시판', 5, 11);
INSERT INTO category (name, parent_idx, child_id)
VALUES ('로제', 5, 14);
