package com.member.bootstrap.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 会员信息
 */

public class MemberModel implements Serializable {

    private static final long serialVersionUID = 7451592598951432099L;
    /**
     * 会员号
     */
    private Long memberId;
    /**
     * 会员昵称
     */
    private String memberName;
    /**
     * 会员密码
     */
    private String password;
    /**
     * 会员注册时间
     */
    private Date createTime;

    /**
     * 会员信息版本
     */
    private String version;

    /**
     * 会员出生年月
     */
    private String dateOfBirth;
    /**
     * 会员等级
     */
    private String memberGrade;

    /**
     * 会员个人账户
     */
    private MemberAccountModel memberAccountModel;

    /**
     * 会员类型
     */
    private List<MemberTypeModel> memberTypeModels;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMemberGrade() {
        return memberGrade;
    }

    public void setMemberGrade(String memberGrade) {
        this.memberGrade = memberGrade;
    }

    public MemberAccountModel getMemberAccountModel() {
        return memberAccountModel;
    }

    public void setMemberAccountModel(MemberAccountModel memberAccountModel) {
        this.memberAccountModel = memberAccountModel;
    }

    public List<MemberTypeModel> getMemberTypeModels() {
        return memberTypeModels;
    }

    public void setMemberTypeModels(List<MemberTypeModel> memberTypeModels) {
        this.memberTypeModels = memberTypeModels;
    }
}
