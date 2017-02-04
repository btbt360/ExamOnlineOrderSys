SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS sys_cases;
DROP TABLE IF EXISTS sys_error;
DROP TABLE IF EXISTS sys_exam_answer;
DROP TABLE IF EXISTS sys_examinee;
DROP TABLE IF EXISTS sys_exampapers_qtypes;
DROP TABLE IF EXISTS sys_exampapers_question;
DROP TABLE IF EXISTS sys_exampapers;
DROP TABLE IF EXISTS sys_exam;
DROP TABLE IF EXISTS sys_exercise_question;
DROP TABLE IF EXISTS sys_exercise;
DROP TABLE IF EXISTS sys_questionoptions;
DROP TABLE IF EXISTS sys_questions;
DROP TABLE IF EXISTS sys_itembank;
DROP TABLE IF EXISTS sys_textbook;
DROP TABLE IF EXISTS sys_subject;




/* Create Tables */

-- 案例表
CREATE TABLE sys_cases
(
	-- 唯一编码
	id varchar(64) NOT NULL COMMENT '唯一编码 : 唯一编码',
	-- 科目编码
	subject_id varchar(64) NOT NULL COMMENT '科目编码 : 科目编码',
	casetitle varchar(2000) NOT NULL COMMENT '案例题目',
	caseanswer varchar(2000) COMMENT '案例答案',
	-- 创建者
	creator_id varchar(64) COMMENT '创建者 : 创建者',
	-- 创建时间
	create_date datetime COMMENT '创建时间 : 创建时间',
	-- 更新时间
	update_date datetime COMMENT '更新时间 : 更新时间',
	update_by varchar(64) COMMENT '更新者',
	-- 备注
	remark varchar(64) COMMENT '备注信息 : 备注',
	-- 0未启用
	-- 1已启用
	isenable int NOT NULL COMMENT '是否启用 : 0未启用
1已启用',
	-- 0已删除 1未删除
	isdel int DEFAULT 0 NOT NULL COMMENT '是否删除 : 0已删除 1未删除',
	name varchar(256) NOT NULL COMMENT '名称',
	PRIMARY KEY (id),
	UNIQUE (id),
	UNIQUE (subject_id)
) COMMENT = '案例表';


-- 错题表
CREATE TABLE sys_error
(
	-- 唯一编码
	id varchar(64) NOT NULL COMMENT '唯一编码 : 唯一编码',
	-- 试题编码
	question_id varchar(64) NOT NULL COMMENT '试题编码 : 试题编码',
	-- 唯一编码
	user_id varchar(64) NOT NULL COMMENT '用户编码 : 唯一编码',
	-- 创建者
	creator_id varchar(64) COMMENT '创建者 : 创建者',
	-- 创建时间
	create_date datetime COMMENT '创建时间 : 创建时间',
	-- 更新时间
	update_date datetime COMMENT '更新时间 : 更新时间',
	update_by varchar(64) COMMENT '更新者',
	-- 备注
	remark varchar(64) COMMENT '备注信息 : 备注',
	-- 0未启用
	-- 1已启用
	isenable int NOT NULL COMMENT '是否启用 : 0未启用
1已启用',
	-- 0已删除 1未删除
	isdel int DEFAULT 0 NOT NULL COMMENT '是否删除 : 0已删除 1未删除',
	PRIMARY KEY (id),
	UNIQUE (id),
	UNIQUE (question_id),
	UNIQUE (user_id)
) COMMENT = '错题表';


-- 考试表
CREATE TABLE sys_exam
(
	-- 唯一编码
	id varchar(64) NOT NULL COMMENT '唯一编码 : 唯一编码',
	code varchar(128) NOT NULL COMMENT '考试编码',
	name varchar(256) NOT NULL COMMENT '名称',
	-- 考试开始时间
	starttime datetime NOT NULL COMMENT '考试开始时间 : 考试开始时间',
	-- 考试结束时间
	endtime datetime NOT NULL COMMENT '考试结束时间 : 考试结束时间',
	duration double DEFAULT 0.0 NOT NULL COMMENT '考试时长',
	address varchar(1000) NOT NULL COMMENT '考试地点',
	number int DEFAULT 0 NOT NULL COMMENT '考试人数',
	invigilatenameone varchar(128) COMMENT '监考员一姓名',
	-- 监考员二姓名
	invigilatenametwo varchar(128) COMMENT '监考员二姓名 : 监考员二姓名',
	demand varchar(256) COMMENT '考试要求',
	institution varchar(2000) COMMENT '考试制度',
	-- 0未开始考试
	-- 1正在考试
	-- 2完成考试
	-- 3未参加考试
	-- 4考试作弊
	status int DEFAULT 0 NOT NULL COMMENT '考试状态 : 0未开始考试
1正在考试
2完成考试
3未参加考试
4考试作弊',
	-- 创建者
	creator_id varchar(64) COMMENT '创建者 : 创建者',
	-- 创建时间
	create_date datetime COMMENT '创建时间 : 创建时间',
	-- 更新时间
	update_date datetime COMMENT '更新时间 : 更新时间',
	update_by varchar(64) COMMENT '更新者',
	-- 备注
	remark varchar(64) COMMENT '备注信息 : 备注',
	-- 0未启用
	-- 1已启用
	isenable int NOT NULL COMMENT '是否启用 : 0未启用
1已启用',
	-- 0已删除 1未删除
	isdel int DEFAULT 0 NOT NULL COMMENT '是否删除 : 0已删除 1未删除',
	PRIMARY KEY (id),
	UNIQUE (id)
) COMMENT = '考试表';


-- 考生表
CREATE TABLE sys_examinee
(
	-- 唯一编码
	id varchar(64) NOT NULL COMMENT '唯一编码 : 唯一编码',
	-- 试卷编码
	exampapers_id varchar(64) NOT NULL COMMENT '试卷编码 : 试卷编码',
	-- 考试编码
	exam_id varchar(64) NOT NULL COMMENT '考试编码 : 考试编码',
	user_id varchar(64) NOT NULL COMMENT '用户编码',
	-- 0未开始考试
	-- 1正在考试
	-- 2完成考试
	-- 3未参加考试
	-- 4考试作弊
	status int DEFAULT 0 NOT NULL COMMENT '考生状态 : 0未开始考试
1正在考试
2完成考试
3未参加考试
4考试作弊',
	fingerprint varchar(2000) NOT NULL COMMENT '指纹编码',
	-- 准考证编码
	ticketcode varchar(1000) NOT NULL COMMENT '准考证编码 : 准考证编码',
	seatno int NOT NULL COMMENT '座位号',
	macaddress varchar(128) NOT NULL COMMENT 'mac地址',
	ipaddress varchar(128) NOT NULL COMMENT 'ip地址',
	photoname varchar(128) NOT NULL COMMENT '照片名称',
	-- 照片内容
	photocontant blob NOT NULL COMMENT '照片内容 : 照片内容',
	extension varchar(128) NOT NULL COMMENT '照片扩展名',
	-- 创建者
	creator_id varchar(64) COMMENT '创建者 : 创建者',
	-- 创建时间
	create_date datetime COMMENT '创建时间 : 创建时间',
	-- 更新时间
	update_date datetime COMMENT '更新时间 : 更新时间',
	update_by varchar(64) COMMENT '更新者',
	-- 备注
	remark varchar(64) COMMENT '备注信息 : 备注',
	-- 0未启用
	-- 1已启用
	isenable int NOT NULL COMMENT '是否启用 : 0未启用
1已启用',
	-- 0已删除 1未删除
	isdel int DEFAULT 0 NOT NULL COMMENT '是否删除 : 0已删除 1未删除',
	PRIMARY KEY (id),
	UNIQUE (id),
	UNIQUE (exampapers_id),
	UNIQUE (exam_id)
) COMMENT = '考生表';


-- 试卷表
CREATE TABLE sys_exampapers
(
	-- 唯一编码
	id varchar(64) NOT NULL COMMENT '唯一编码 : 唯一编码',
	-- 考试编码
	exam_id varchar(64) NOT NULL COMMENT '考试编码 : 考试编码',
	-- 试卷编码
	code varchar(128) NOT NULL COMMENT '试卷编码 : 试卷编码',
	name varchar(256) NOT NULL COMMENT '名称',
	sumscore int DEFAULT 0 NOT NULL COMMENT '试卷总分',
	usecount int DEFAULT 0 NOT NULL COMMENT '试卷使用次数',
	-- 试卷总题数
	sumquestion int DEFAULT 0 NOT NULL COMMENT '试卷总题数 : 试卷总题数',
	-- 创建者
	creator_id varchar(64) COMMENT '创建者 : 创建者',
	-- 创建时间
	create_date datetime COMMENT '创建时间 : 创建时间',
	-- 更新时间
	update_date datetime COMMENT '更新时间 : 更新时间',
	update_by varchar(64) COMMENT '更新者',
	-- 备注
	remark varchar(64) COMMENT '备注信息 : 备注',
	-- 0未启用
	-- 1已启用
	isenable int NOT NULL COMMENT '是否启用 : 0未启用
1已启用',
	-- 0已删除 1未删除
	isdel int DEFAULT 0 NOT NULL COMMENT '是否删除 : 0已删除 1未删除',
	PRIMARY KEY (id),
	UNIQUE (id),
	UNIQUE (exam_id)
) COMMENT = '试卷表';


-- 试卷题型表
CREATE TABLE sys_exampapers_qtypes
(
	-- 唯一编码
	id varchar(64) NOT NULL COMMENT '唯一编码 : 唯一编码',
	typename varchar(128) NOT NULL COMMENT '类型名称',
	sumtotal int DEFAULT 0 NOT NULL COMMENT '类型试题总数',
	sumscores int NOT NULL COMMENT '类型试题总分数',
	-- 试卷编码
	exampapers_id varchar(64) NOT NULL COMMENT '试卷编码 : 试卷编码',
	PRIMARY KEY (id),
	UNIQUE (id),
	UNIQUE (exampapers_id)
) COMMENT = '试卷题型表';


-- 试卷试题对照表
CREATE TABLE sys_exampapers_question
(
	-- 唯一编码
	id varchar(64) NOT NULL COMMENT '唯一编码 : 唯一编码',
	-- 试卷编码
	exampapers_id varchar(64) NOT NULL COMMENT '试卷编码 : 试卷编码',
	-- 试题编码
	question_id varchar(64) NOT NULL COMMENT '试题编码 : 试题编码',
	scores int DEFAULT 0 NOT NULL COMMENT '单题分数',
	PRIMARY KEY (id),
	UNIQUE (id),
	UNIQUE (exampapers_id),
	UNIQUE (question_id)
) COMMENT = '试卷试题对照表';


-- 考生试卷答案表
CREATE TABLE sys_exam_answer
(
	-- 唯一编码
	id varchar(64) NOT NULL COMMENT '唯一编码 : 唯一编码',
	-- 唯一编码
	examinee_id varchar(64) NOT NULL COMMENT '考生编码 : 唯一编码',
	-- 考试编码
	exam_id varchar(64) NOT NULL COMMENT '考试编码 : 考试编码',
	-- 唯一编码
	question_id varchar(64) NOT NULL COMMENT '试题编码 : 唯一编码',
	answerinfo varchar(2000) NOT NULL COMMENT '答案内容',
	scores int DEFAULT 0 NOT NULL COMMENT '答案分数',
	-- 0自动判卷
	-- 1手动判卷
	judgetype int DEFAULT 0 NOT NULL COMMENT '判卷类型 : 0自动判卷
1手动判卷',
	-- 判卷人
	judgepeopleid varchar(64) COMMENT '判卷人 : 判卷人',
	judgepeoplename varchar(128) COMMENT '判卷人姓名',
	judgetime datetime NOT NULL COMMENT '判卷时间',
	-- 创建者
	creator_id varchar(64) COMMENT '创建者 : 创建者',
	-- 创建时间
	create_date datetime COMMENT '创建时间 : 创建时间',
	-- 更新时间
	update_date datetime COMMENT '更新时间 : 更新时间',
	update_by varchar(64) COMMENT '更新者',
	-- 备注
	remark varchar(64) COMMENT '备注信息 : 备注',
	-- 0未启用
	-- 1已启用
	isenable int NOT NULL COMMENT '是否启用 : 0未启用
1已启用',
	-- 0已删除 1未删除
	isdel int DEFAULT 0 NOT NULL COMMENT '是否删除 : 0已删除 1未删除',
	PRIMARY KEY (id),
	UNIQUE (examinee_id),
	UNIQUE (exam_id),
	UNIQUE (question_id)
) COMMENT = '考生试卷答案表';


-- 练习表 : 练习表
CREATE TABLE sys_exercise
(
	-- 唯一编码
	id varchar(64) NOT NULL COMMENT '唯一编码 : 唯一编码',
	-- 用户编码
	user_id varchar(64) NOT NULL COMMENT '用户编码 : 用户编码',
	subject_id varchar(64) NOT NULL COMMENT '科目编码',
	itembank_id varchar(64) NOT NULL COMMENT '题库编码',
	sumcount int DEFAULT 0 NOT NULL COMMENT '练习总数',
	-- 已完成总数
	alreadycount int DEFAULT 0 NOT NULL COMMENT '已完成总数 : 已完成总数',
	name varchar(256) NOT NULL COMMENT '名称',
	-- 创建者
	creator_id varchar(64) COMMENT '创建者 : 创建者',
	-- 创建时间
	create_date datetime COMMENT '创建时间 : 创建时间',
	-- 更新时间
	update_date datetime COMMENT '更新时间 : 更新时间',
	update_by varchar(64) COMMENT '更新者',
	-- 备注
	remark varchar(64) COMMENT '备注信息 : 备注',
	-- 0未启用
	-- 1已启用
	isenable int NOT NULL COMMENT '是否启用 : 0未启用
1已启用',
	-- 0已删除 1未删除
	isdel int DEFAULT 0 NOT NULL COMMENT '是否删除 : 0已删除 1未删除',
	PRIMARY KEY (id),
	UNIQUE (id),
	UNIQUE (user_id)
) COMMENT = '练习表 : 练习表';


-- 练习试题对照表
CREATE TABLE sys_exercise_question
(
	-- 唯一编码
	id varchar(64) NOT NULL COMMENT '唯一编码 : 唯一编码',
	-- 练习编码
	exercise_id varchar(64) NOT NULL COMMENT '练习编码 : 练习编码',
	-- 试题编码
	question_id varchar(64) NOT NULL COMMENT '试题编码 : 试题编码',
	PRIMARY KEY (id),
	UNIQUE (id),
	UNIQUE (exercise_id),
	UNIQUE (question_id)
) COMMENT = '练习试题对照表';


-- 题库表
CREATE TABLE sys_itembank
(
	-- 唯一编码
	id varchar(64) NOT NULL COMMENT '唯一编码 : 唯一编码',
	-- 科目编码
	subject_id varchar(64) NOT NULL COMMENT '科目编码 : 科目编码',
	-- 总题数
	sumtotal int DEFAULT 0 COMMENT '总题数 : 总题数',
	-- 选择次数
	selectnum int DEFAULT 0 NOT NULL COMMENT '选择次数 : 选择次数',
	name varchar(256) NOT NULL COMMENT '名称',
	-- 创建者
	creator_id varchar(64) COMMENT '创建者 : 创建者',
	-- 创建时间
	create_date datetime COMMENT '创建时间 : 创建时间',
	-- 更新时间
	update_date datetime COMMENT '更新时间 : 更新时间',
	update_by varchar(64) COMMENT '更新者',
	-- 备注
	remark varchar(64) COMMENT '备注信息 : 备注',
	-- 0未启用
	-- 1已启用
	isenable int NOT NULL COMMENT '是否启用 : 0未启用
1已启用',
	-- 0已删除 1未删除
	isdel int DEFAULT 0 NOT NULL COMMENT '是否删除 : 0已删除 1未删除',
	PRIMARY KEY (id),
	UNIQUE (id),
	UNIQUE (subject_id)
) COMMENT = '题库表';


-- 试题选项表
CREATE TABLE sys_questionoptions
(
	-- 唯一编码
	id varchar(64) NOT NULL COMMENT '唯一编码 : 唯一编码',
	-- 试题编码
	questions_id varchar(64) NOT NULL COMMENT '试题编码 : 试题编码',
	-- 例如A B C D
	code varchar(2) NOT NULL COMMENT '选项编码 : 例如A B C D',
	-- 选项内容
	contant varchar(2000) NOT NULL COMMENT '选项内容 : 选项内容',
	-- 创建者
	creator_id varchar(64) COMMENT '创建者 : 创建者',
	-- 创建时间
	create_date datetime COMMENT '创建时间 : 创建时间',
	-- 更新时间
	update_date datetime COMMENT '更新时间 : 更新时间',
	update_by varchar(64) COMMENT '更新者',
	-- 备注
	remark varchar(64) COMMENT '备注信息 : 备注',
	-- 0未启用
	-- 1已启用
	isenable int NOT NULL COMMENT '是否启用 : 0未启用
1已启用',
	-- 0已删除 1未删除
	isdel int DEFAULT 0 NOT NULL COMMENT '是否删除 : 0已删除 1未删除',
	PRIMARY KEY (id),
	UNIQUE (id),
	UNIQUE (questions_id)
) COMMENT = '试题选项表';


-- 试题表题头表
CREATE TABLE sys_questions
(
	-- 唯一编码
	id varchar(64) NOT NULL COMMENT '唯一编码 : 唯一编码',
	-- 唯一编码
	itembank_id varchar(64) NOT NULL COMMENT '题库编码 : 唯一编码',
	code varchar(64) NOT NULL COMMENT '试题编码',
	name varchar(256) NOT NULL COMMENT '名称',
	-- 试题标题
	title varchar(1000) NOT NULL COMMENT '试题标题 : 试题标题',
	-- 试题内容
	contant blob COMMENT '试题内容 : 试题内容',
	-- 0 字符类型
	-- 1 jpg
	-- 2 png
	-- 3 avi
	-- 4 mp4
	-- 5 flv
	-- 6 mp3
	extensiontype int DEFAULT 0 NOT NULL COMMENT '试题内容扩展名类型 : 0 字符类型
1 jpg
2 png
3 avi
4 mp4
5 flv
6 mp3',
	info varchar(2000) COMMENT '试题描述',
	-- 0单选、1多选、2判断、3问答、4填空、5阅读理解、6打字题、7组合
	-- 
	questiontype int DEFAULT 0 NOT NULL COMMENT '试题类型 : 0单选、1多选、2判断、3问答、4填空、5阅读理解、6打字题、7组合
',
	-- 0文本题
	-- 1图文题
	-- 2音频题
	-- 3动画题
	headertype int DEFAULT 0 NOT NULL COMMENT '题头类型 : 0文本题
1图文题
2音频题
3动画题',
	questionanswer varchar(2000) NOT NULL COMMENT '试题答案',
	questionanswerinfo varchar(2000) COMMENT '试题解答',
	-- 创建者
	creator_id varchar(64) COMMENT '创建者 : 创建者',
	-- 创建时间
	create_date datetime COMMENT '创建时间 : 创建时间',
	-- 更新时间
	update_date datetime COMMENT '更新时间 : 更新时间',
	update_by varchar(64) COMMENT '更新者',
	-- 备注
	remark varchar(64) COMMENT '备注信息 : 备注',
	-- 0未启用
	-- 1已启用
	isenable int NOT NULL COMMENT '是否启用 : 0未启用
1已启用',
	-- 0已删除 1未删除
	isdel int DEFAULT 0 NOT NULL COMMENT '是否删除 : 0已删除 1未删除',
	PRIMARY KEY (id),
	UNIQUE (id),
	UNIQUE (itembank_id)
) COMMENT = '试题表题头表';


-- 科目表
CREATE TABLE sys_subject
(
	-- 唯一编码
	id varchar(64) NOT NULL COMMENT '唯一编码 : 唯一编码',
	info varchar(1000) COMMENT '科目描述',
	code varchar(64) NOT NULL COMMENT '科目编码',
	-- 名称
	name varchar(256) NOT NULL COMMENT '名称 : 名称',
	-- 父级编码
	parentid varchar(64) COMMENT '父级编码 : 父级编码',
	parentpath varchar(2000) COMMENT '所有父级编码路径',
	-- 排序
	sort double DEFAULT 0 COMMENT '排序 : 排序',
	-- 创建者
	creator_id varchar(64) COMMENT '创建者 : 创建者',
	-- 创建时间
	create_date datetime COMMENT '创建时间 : 创建时间',
	-- 更新时间
	update_date datetime COMMENT '更新时间 : 更新时间',
	update_by varchar(64) COMMENT '更新者',
	-- 备注
	remark varchar(64) COMMENT '备注信息 : 备注',
	-- 0未启用
	-- 1已启用
	isenable int NOT NULL COMMENT '是否启用 : 0未启用
1已启用',
	-- 0已删除 1未删除
	isdel int DEFAULT 0 NOT NULL COMMENT '是否删除 : 0已删除 1未删除',
	PRIMARY KEY (id),
	UNIQUE (id)
) COMMENT = '科目表';


-- 教材表
CREATE TABLE sys_textbook
(
	-- 唯一编码
	id varchar(64) NOT NULL COMMENT '唯一编码 : 唯一编码',
	-- 科目编码
	subject_id varchar(64) NOT NULL COMMENT '科目编码 : 科目编码',
	-- 创建者
	creator_id varchar(64) COMMENT '创建者 : 创建者',
	-- 创建时间
	create_date datetime COMMENT '创建时间 : 创建时间',
	-- 更新时间
	update_date datetime COMMENT '更新时间 : 更新时间',
	update_by varchar(64) COMMENT '更新者',
	-- 备注
	remark varchar(64) COMMENT '备注信息 : 备注',
	-- 0未启用
	-- 1已启用
	isenable int NOT NULL COMMENT '是否启用 : 0未启用
1已启用',
	-- 0已删除 1未删除
	isdel int DEFAULT 0 NOT NULL COMMENT '是否删除 : 0已删除 1未删除',
	code varchar(128) NOT NULL COMMENT '教材编码',
	issuedate date NOT NULL COMMENT '发布日期',
	editorinchief varchar(256) COMMENT '主编名称',
	PRIMARY KEY (id),
	UNIQUE (id),
	UNIQUE (subject_id)
) COMMENT = '教材表';



/* Create Foreign Keys */

ALTER TABLE sys_examinee
	ADD FOREIGN KEY (exam_id)
	REFERENCES sys_exam (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sys_exampapers
	ADD FOREIGN KEY (exam_id)
	REFERENCES sys_exam (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sys_exam_answer
	ADD FOREIGN KEY (exam_id)
	REFERENCES sys_exam (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sys_exam_answer
	ADD FOREIGN KEY (examinee_id)
	REFERENCES sys_examinee (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sys_examinee
	ADD FOREIGN KEY (exampapers_id)
	REFERENCES sys_exampapers (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sys_exampapers_qtypes
	ADD FOREIGN KEY (exampapers_id)
	REFERENCES sys_exampapers (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sys_exampapers_question
	ADD FOREIGN KEY (exampapers_id)
	REFERENCES sys_exampapers (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sys_exercise_question
	ADD FOREIGN KEY (exercise_id)
	REFERENCES sys_exercise (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sys_questions
	ADD FOREIGN KEY (itembank_id)
	REFERENCES sys_itembank (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sys_error
	ADD FOREIGN KEY (question_id)
	REFERENCES sys_questions (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sys_exampapers_question
	ADD FOREIGN KEY (question_id)
	REFERENCES sys_questions (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sys_exam_answer
	ADD FOREIGN KEY (question_id)
	REFERENCES sys_questions (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sys_exercise_question
	ADD FOREIGN KEY (question_id)
	REFERENCES sys_questions (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sys_questionoptions
	ADD FOREIGN KEY (questions_id)
	REFERENCES sys_questions (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sys_cases
	ADD FOREIGN KEY (subject_id)
	REFERENCES sys_subject (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sys_itembank
	ADD FOREIGN KEY (subject_id)
	REFERENCES sys_subject (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sys_textbook
	ADD FOREIGN KEY (subject_id)
	REFERENCES sys_subject (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sys_error
	ADD FOREIGN KEY (user_id)
	REFERENCES sys_user (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sys_exercise
	ADD FOREIGN KEY (user_id)
	REFERENCES sys_user (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



