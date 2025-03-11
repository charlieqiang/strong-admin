create database strong_admin;
use strong_admin;

DROP TABLE IF EXISTS user;
create table user
(
    id          varchar(64)           not null comment '主键' primary key,
    account     varchar(255)          not null comment '账号',
    password    varchar(255)          not null comment '密码（加密存储）',
    username    varchar(50)           null comment '用户名',
    avatar      varchar(500)          null comment '头像',
    dept_id     varchar(64)           null comment '部门 ID',
    phone       varchar(50)           null comment '手机号',
    hired_date  datetime              null comment '入职时间',
    use_status  tinyint(1)  default 1 not null comment '启用状态（1-启用，0-禁用）',
    creator     varchar(64)           null comment '创建人',
    create_time datetime              null comment '创建时间',
    modifier    varchar(64)           null comment '修改人',
    modify_time datetime              null comment '修改时间',
    deleted     smallint(1) default 0 not null comment '逻辑删除，0否，1是',
    constraint idx_account
        unique (account)
);

INSERT INTO user (id, account, password, username, avatar, dept_id, phone, hired_date, use_status)
VALUES ('1337099190876628888', 'admin', '$2a$10$wG6s3Cba0wW3wWR9/RCLMekri1hObqryKabOfU6snjKT33BoFunT.', 'admin',
        'http://cdn.chenlinqiang.cn/placeholder/avatar.gif',
        null, null, null, 1);

DROP TABLE IF EXISTS dept;
create table dept
(
    id             varchar(64)           not null comment '主键' primary key,
    parent_id      varchar(64)           null comment '父级部门',
    name           varchar(50)           not null comment '部门名称',
    leader_user_id varchar(200)          null comment '部门负责人id',
    sort           bigint                null comment '序号',
    creator        varchar(64)           null comment '创建人',
    create_time    datetime              null comment '创建时间',
    modifier       varchar(64)           null comment '修改人',
    modify_time    datetime              null comment '修改时间',
    deleted        smallint(1) default 0 not null comment '逻辑删除，0否，1是'
);

DROP TABLE IF EXISTS menu;
create table if not exists menu
(
    id          varchar(64)          not null comment '主键' primary key,
    name        varchar(100)         not null comment '菜单名称',
    code        varchar(50)          not null comment '编码',
    path        varchar(200)         null comment '路径',
    parent_id   bigint               null comment '父级菜单',
    sort        tinyint              null comment '排序',
    use_status  tinyint(1) default 1 not null comment '使用状态:1启用,2禁用  ',
    type        tinyint              not null comment '类型:1菜单,2按钮',
    creator     varchar(64)          null comment '创建人',
    create_time datetime             null comment '创建时间',
    modifier    varchar(64)          null comment '修改人',
    modify_time datetime             null comment '修改时间',
    deleted     tinyint(1) default 0 not null comment '逻辑删除，0否，1是'
);

INSERT INTO menu (id, name, code, path, parent_id, sort, use_status, type, creator, create_time, modifier,
                  modify_time, deleted)
VALUES ('1347614852273360896', '首页', 'dashboard', '/', null, 0, 1, 1, null, null, null, null, 0);


DROP TABLE IF EXISTS role;
create table if not exists role
(
    id          varchar(64)          not null comment '主键' primary key,
    name        varchar(100)         not null comment '角色名称',
    code        varchar(100)         not null comment '角色编码',
    creator     varchar(64)          null comment '创建人',
    create_time datetime             null comment '创建时间',
    modifier    varchar(64)          null comment '修改人',
    modify_time datetime             null comment '修改时间',
    deleted     tinyint(1) default 0 not null comment '逻辑删除，0否，1是'
);

INSERT INTO role (id, name, code, creator, create_time, modifier, modify_time, deleted)
VALUES ('1347614852273360898', '管理员', 'admin', null, null, null, null, 0);

DROP TABLE IF EXISTS role_menu;
create table if not exists role_menu
(
    id          varchar(64)          not null comment '主键' primary key,
    role_id     varchar(64)          not null comment '角色主键',
    menu_id     varchar(64)          not null comment '菜单主键',
    creator     varchar(64)          null comment '创建人',
    create_time datetime             null comment '创建时间',
    modifier    varchar(64)          null comment '修改人',
    modify_time datetime             null comment '修改时间',
    deleted     tinyint(1) default 0 not null comment '逻辑删除，0否，1是'
);

INSERT INTO role_menu (id, role_id, menu_id, creator, create_time, modifier, modify_time, deleted)
VALUES ('1347614852273360899', '1347614852273360898', '1347614852273360896', null, null, null, null, 0);

DROP TABLE IF EXISTS user_role;
create table if not exists user_role
(
    id          varchar(64)          not null comment '主键' primary key,
    user_id     varchar(64)          not null comment '用户主键',
    role_id     varchar(64)          not null comment '角色主键',
    creator     varchar(64)          null comment '创建人',
    create_time datetime             null comment '创建时间',
    modifier    varchar(64)          null comment '修改人',
    modify_time datetime             null comment '修改时间',
    deleted     tinyint(1) default 0 not null comment '逻辑删除，0否，1是'
);

INSERT INTO user_role (id, user_id, role_id, creator, create_time, modifier, modify_time, deleted)
VALUES ('1348644702941491208', '1337099190876628888', '1347614852273360898', null, null, null, null, DEFAULT);
