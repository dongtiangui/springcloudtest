package com.member.bootstrap.mapper;

import com.member.bootstrap.model.MemberModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberInterface {

    List<MemberModel> findAll();

    MemberModel findOneMemberModel(String memberName);
}
