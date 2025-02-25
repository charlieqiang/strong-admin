create database strong_admin;
use strong_admin;

-- auto-generated definition
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

