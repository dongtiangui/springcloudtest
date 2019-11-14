package com.member.bootstrap.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 会员类型
 */
public class MemberTypeModel implements Serializable {

    private static final long serialVersionUID = 7894804984159748907L;

    private Long typeId;

    private String memberTypeName;

    private Date createTime;

    private String description;

    private List<MemberModel> memberModels;

    private List<MemberPermissionModel> permissionModels;

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getMemberTypeName() {
        return memberTypeName;
    }

    public void setMemberTypeName(String memberTypeName) {
        this.memberTypeName = memberTypeName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MemberModel> getMemberModels() {
        return memberModels;
    }

    public void setMemberModels(List<MemberModel> memberModels) {
        this.memberModels = memberModels;
    }

    public List<MemberPermissionModel> getPermissionModels() {
        return permissionModels;
    }

    public void setPermissionModels(List<MemberPermissionModel> permissionModels) {
        this.permissionModels = permissionModels;
    }
}
