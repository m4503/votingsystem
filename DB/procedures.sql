DELIMITER $$
CREATE PROCEDURE add_vote_item(IN item_name VARCHAR(100))
BEGIN
    INSERT INTO vote_item(name) VALUES(item_name);
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE vote_for_item(IN voter VARCHAR(100), IN item_id INT)
BEGIN
    INSERT IGNORE INTO vote_record(voter_name, vote_item_id) VALUES(voter, item_id);
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE get_vote_results()
BEGIN
    SELECT vi.id, vi.name, COUNT(vr.id) AS vote_count
    FROM vote_item vi
    LEFT JOIN vote_record vr ON vi.id = vr.vote_item_id
    GROUP BY vi.id, vi.name
    ORDER BY vote_count DESC;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE delete_vote_item(IN item_id INT)
BEGIN
    DELETE FROM vote_item WHERE id = item_id;
END$$
DELIMITER ;