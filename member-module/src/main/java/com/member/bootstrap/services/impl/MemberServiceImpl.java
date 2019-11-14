package com.member.bootstrap.services.impl;

import com.member.bootstrap.mapper.MemberInterface;
import com.member.bootstrap.model.MemberModel;
import com.member.bootstrap.services.MemberServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.NEVER)
public class MemberServiceImpl implements MemberServiceInterface {

    @Autowired
    private MemberInterface memberInterface;

    @Override
    public MemberModel login(String username) {
        return memberInterface.findOneMemberModel(username);
    }
}
