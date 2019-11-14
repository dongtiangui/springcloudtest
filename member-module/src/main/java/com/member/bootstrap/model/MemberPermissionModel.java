package com.member.bootstrap.model;

import java.util.List;

/**
 * 会员权限
 */
public class MemberPermissionModel {
    private static final long serialVersionUID = 7779609709399345126L;
    private Long permissionId;
    private String permission_name;
    private String permission_description;
    private String permission_type;
    private String permission_url;
    private List<MemberTypeModel> memberTypeModels;

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermission_name() {
        return permission_name;
    }

    public void setPermission_name(String permission_name) {
        this.permission_name = permission_name;
    }

    public String getPermission_description() {
        return permission_description;
    }

    public void setPermission_description(String permission_description) {
        this.permission_description = permission_description;
    }

    public String getPermission_type() {
        return permission_type;
    }

    public void setPermission_type(String permission_type) {
        this.permission_type = permission_type;
    }

    public String getPermission_url() {
        return permission_url;
    }

    public void setPermission_url(String permission_url) {
        this.permission_url = permission_url;
    }

    public List<MemberTypeModel> getMemberTypeModels() {
        return memberTypeModels;
    }

    public void setMemberTypeModels(List<MemberTypeModel> memberTypeModels) {
        this.memberTypeModels = memberTypeModels;
    }
}
