CREATE TABLE `t_serial` (
  `serial_id` varchar(16) NOT NULL COMMENT '序列标识',
  `min_val` bigint(20) DEFAULT '0' COMMENT '最小数值',
  `max_val` bigint(20) DEFAULT '0' COMMENT '最大数值',
  `last_val` bigint(20) DEFAULT '0' COMMENT '上次数值',
  `physical_date` int(11) DEFAULT '0' COMMENT '物理日期',
  `remark` varchar(128) DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`serial_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统序列器';


INSERT INTO t_serial (serial_id,min_val,max_val,last_val,physical_date,remark) VALUES
('SEQ_USER_ID',0,9999999,0,20211025,'用户ID序列');
