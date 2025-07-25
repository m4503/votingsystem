-- 插入投票項目
INSERT INTO vote_item (name) VALUES ('電腦');
INSERT INTO vote_item (name) VALUES ('滑鼠');

-- 插入投票紀錄
INSERT INTO vote_record (voter_name, vote_item_id) VALUES ('Leo', 1);
INSERT INTO vote_record (voter_name, vote_item_id) VALUES ('Sandy', 1);
INSERT INTO vote_record (voter_name, vote_item_id) VALUES ('Sandy', 2);
INSERT INTO vote_record (voter_name, vote_item_id) VALUES ('Randy', 2);
INSERT INTO vote_record (voter_name, vote_item_id) VALUES ('RSY', 2);