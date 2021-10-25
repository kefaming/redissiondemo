CREATE PROCEDURE `p_serial`(IN p_serial_id VARCHAR(64))
BEGIN
	DECLARE o_retcode INT DEFAULT 0;
    DECLARE o_retmsg VARCHAR(64) DEFAULT '';
    DECLARE o_min_val BIGINT DEFAULT 0;
    DECLARE o_max_val BIGINT DEFAULT 0;
    DECLARE o_last_val BIGINT DEFAULT 0;
    DECLARE o_next_val BIGINT DEFAULT 0;
    DECLARE o_phy_date INTEGER DEFAULT 0;
    DECLARE o_cur_date INTEGER DEFAULT 0;
    SET o_cur_date = CURDATE() + 0;

    START TRANSACTION;
    main:BEGIN

	    SELECT min_val, max_val, last_val, physical_date
	    	INTO o_min_val, o_max_val, o_last_val, o_phy_date
		FROM `t_serial`
		WHERE serial_id = p_serial_id;

        IF o_last_val = '' THEN
            SET o_retcode = 1;
            SET o_retmsg = CONCAT('序列器',p_serial_id,'不存在');
            LEAVE main;
        END IF;

        IF (o_last_val >= o_max_val) THEN
            SET o_retcode = 2;
            SET o_retmsg = CONCAT('序列器',p_serial_id,'已达到最大数值');
            LEAVE main;
        END IF;

        UPDATE `t_serial` SET last_val = last_val + 1 WHERE serial_id = p_serial_id;

        SELECT last_val, physical_date INTO o_last_val, o_phy_date FROM `t_serial` WHERE serial_id = p_serial_id;

        SET o_next_val = o_last_val;

        LEAVE main;
    END;

    IF o_retcode = 0 THEN
        COMMIT;
    ELSE
        ROLLBACK;
    END IF;

    SELECT o_retcode AS retcode, o_retmsg AS retmsg, o_next_val AS next_val;
END;
