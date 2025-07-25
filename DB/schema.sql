CREATE DATABASE voting_system DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE vote_item (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE vote_record (
    id INT PRIMARY KEY AUTO_INCREMENT,
    voter_name VARCHAR(100) NOT NULL,
    vote_item_id INT NOT NULL,
    FOREIGN KEY (vote_item_id) REFERENCES vote_item(id) ON DELETE CASCADE,
    UNIQUE (voter_name, vote_item_id) -- 防止同一使用者對同一項目重複投票
);