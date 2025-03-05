create database strong_admin;
use strong_admin;

DROP TABLE IF EXISTS user;
create table user
(
    id         varchar(64)          not null comment '主键'
        primary key,
    account    varchar(255)         not null comment '账号',
    password   varchar(255)         not null comment '密码（加密存储）',
    username   varchar(50)          null comment '用户名',
    avatar     varchar(500)         null comment '头像',
    dept_id    varchar(64)          not null comment '部门 ID',
    phone      varchar(50)          null comment '手机号',
    hired_date datetime             null comment '入职时间',
    use_status tinyint(1) default 1 not null comment '启用状态（1-启用，0-禁用）',
    constraint idx_account
        unique (account)
);

INSERT INTO user (id, account, password, username, avatar, dept_id, phone, hired_date, use_status)
VALUES ('1337099190874624000', 'admin', '$2a$10$wG6s3Cba0wW3wWR9/RCLMekri1hObqryKabOfU6snjKT33BoFunT.', 'admin', 'http://cdn.chenlinqiang.cn/placeholder/avatar.gif',
        '1337099190874624000', null, null, 1);
