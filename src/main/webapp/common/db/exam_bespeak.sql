SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS sys_bespeakexam_examinee;
DROP TABLE IF EXISTS sys_bespeakexam;




/* Create Tables */

CREATE TABLE sys_bespeakexam
(
	-- 唯一编码
	id varchar(64) NOT NULL COMMENT '唯一编码',
	examcode varchar(128) NOT NULL,
	-- 考试名称
	examname varchar(64) NOT NULL COMMENT '考试名称',
	-- 考试开始时间
	starttime datetime NOT NULL COMMENT '考试开始时间',
	-- 考试结束时间
	endtime datetime NOT NULL COMMENT '考试结束时间',
	duration double DEFAULT 0.0 NOT NULL,
	address varchar(1000) NOT NULL,
	number int DEFAULT 0 NOT NULL,
	demand varchar(256),
	institution varchar(2000),
	-- 0未开始考试
	-- 1正在考试
	-- 2完成考试
	-- 3未参加考试
	-- 4考试作弊
	status int DEFAULT 0 NOT NULL COMMENT '0未开始考试
1正在考试
2完成考试
3未参加考试
4考试作弊',
	-- 创建者
	creator_id varchar(64) COMMENT '创建者',
	-- 创建时间
	create_date datetime COMMENT '创建时间',
	-- 更新时间
	update_date datetime COMMENT '更新时间',
	update_by varchar(64),
	-- 备注
	remark varchar(64) COMMENT '备注',
	-- 0未启用
	-- 1已启用
	isenable int DEFAULT 0 NOT NULL COMMENT '0未启用
1已启用',
	-- 0已删除 1未删除
	isdel int DEFAULT 0 NOT NULL COMMENT '0已删除 1未删除',
	PRIMARY KEY (id),
	UNIQUE (id)
);


CREATE TABLE sys_bespeakexam_examinee
(
	id varchar(64) NOT NULL,
	-- 唯一编码
	exam_id varchar(64) NOT NULL COMMENT '唯一编码',
	examinee_id varchar(64) NOT NULL,
	PRIMARY KEY (id),
	UNIQUE (id)
);



/* Create Foreign Keys */

ALTER TABLE sys_bespeakexam_examinee
	ADD FOREIGN KEY (exam_id)
	REFERENCES sys_bespeakexam (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



