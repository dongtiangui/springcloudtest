# 系统用户表
CREATE TABLE t_system_user (
                               id varchar(50) NOT NULL COMMENT '主键',
                               username varchar(50) DEFAULT NULL COMMENT '用户名',
                               password varchar(500) DEFAULT NULL COMMENT '密码',
                               enabled bit(1) DEFAULT NULL COMMENT '是否可用',
                               cjr_id varchar(50) DEFAULT NULL COMMENT '创建人id',
                               cjr varchar(50) DEFAULT NULL COMMENT '创建人',
                               cj_sj datetime DEFAULT NULL COMMENT '创建时间',
                               gxr_id varchar(50) DEFAULT NULL COMMENT '更新人id',
                               gxr varchar(50) DEFAULT NULL COMMENT '更新人',
                               gx_sj datetime DEFAULT NULL COMMENT '更新时间',
                               PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 系统权限表
CREATE TABLE t_system_privilege (
                                    id varchar(50) NOT NULL COMMENT '主键',
                                    code varchar(50) DEFAULT NULL COMMENT '编码',
                                    name varchar(50) DEFAULT NULL COMMENT '名称',
                                    cjr_id varchar(50) DEFAULT NULL COMMENT '创建人id',
                                    cjr varchar(50) DEFAULT NULL COMMENT '创建人',
                                    cj_sj datetime DEFAULT NULL COMMENT '创建时间',
                                    gxr_id varchar(50) DEFAULT NULL COMMENT '更新人id',
                                    gxr varchar(50) DEFAULT NULL COMMENT '更新人',
                                    gx_sj datetime DEFAULT NULL COMMENT '更新时间',
                                    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 系统用户权限关系表
CREATE TABLE t_system_user_privilege (
                                         id varchar(50) NOT NULL COMMENT '主键',
                                         username varchar(50) DEFAULT NULL COMMENT '用户名',
                                         privilege_code varchar(50) DEFAULT NULL COMMENT '权限编码',
                                         cjr_id varchar(50) DEFAULT NULL COMMENT '创建人id',
                                         cjr varchar(50) DEFAULT NULL COMMENT '创建人',
                                         cj_sj datetime DEFAULT NULL COMMENT '创建时间',
                                         gxr_id varchar(50) DEFAULT NULL COMMENT '更新人id',
                                         gxr varchar(50) DEFAULT NULL COMMENT '更新人',
                                         gx_sj datetime DEFAULT NULL COMMENT '更新时间',
                                         PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 系统角色表
CREATE TABLE t_system_role (
                               id varchar(50) NOT NULL COMMENT '主键',
                               name varchar(50) DEFAULT NULL COMMENT '名称',
                               cjr_id varchar(50) DEFAULT NULL COMMENT '创建人id',
                               cjr varchar(50) DEFAULT NULL COMMENT '创建人',
                               cj_sj datetime DEFAULT NULL COMMENT '创建时间',
                               gxr_id varchar(50) DEFAULT NULL COMMENT '更新人id',
                               gxr varchar(50) DEFAULT NULL COMMENT '更新人',
                               gx_sj datetime DEFAULT NULL COMMENT '更新时间',
                               PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 系统角色权限关系表
CREATE TABLE t_system_role_privilege (
                                         id varchar(50) NOT NULL COMMENT '主键',
                                         role_id varchar(50) DEFAULT NULL COMMENT '角色id',
                                         privilege_code varchar(50) DEFAULT NULL COMMENT '权限编码',
                                         cjr_id varchar(50) DEFAULT NULL COMMENT '创建人id',
                                         cjr varchar(50) DEFAULT NULL COMMENT '创建人',
                                         cj_sj datetime DEFAULT NULL COMMENT '创建时间',
                                         gxr_id varchar(50) DEFAULT NULL COMMENT '更新人id',
                                         gxr varchar(50) DEFAULT NULL COMMENT '更新人',
                                         gx_sj datetime DEFAULT NULL COMMENT '更新时间',
                                         PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 系统用户角色关系表
CREATE TABLE t_system_user_role (
                                    id varchar(50) NOT NULL COMMENT '主键',
                                    username varchar(50) DEFAULT NULL COMMENT '用户名',
                                    role_id varchar(50) DEFAULT NULL COMMENT '角色id',
                                    cjr_id varchar(50) DEFAULT NULL COMMENT '创建人id',
                                    cjr varchar(50) DEFAULT NULL COMMENT '创建人',
                                    cj_sj datetime DEFAULT NULL COMMENT '创建时间',
                                    gxr_id varchar(50) DEFAULT NULL COMMENT '更新人id',
                                    gxr varchar(50) DEFAULT NULL COMMENT '更新人',
                                    gx_sj datetime DEFAULT NULL COMMENT '更新时间',
                                    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
