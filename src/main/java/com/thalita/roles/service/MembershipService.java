package com.thalita.roles.service;

import com.thalita.roles.model.MembershipId;
import com.thalita.roles.repository.MembershipRepository;
import com.thalita.roles.model.MembershipModel;
import com.thalita.roles.service.validations.MembershipValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class MembershipService {

    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private MembershipValidations membershipValidations;

    public MembershipService() {

    }


    public MembershipService(MembershipRepository membershipRepository, MembershipValidations membershipValidations) {
        this.membershipRepository = membershipRepository;
        this.membershipValidations = membershipValidations;
    }

    public MembershipModel assignRoleToMembership(MembershipModel membershipModel) {
        membershipValidations.validations(membershipModel);
        return membershipRepository.save(membershipModel);
    }

   public List<MembershipModel> findByRoleId(Long roleId) {
        return membershipRepository.findByRoleId(roleId);
    }

     public Long findByMembershipId(String userId, String teamId) {
        MembershipId membershipId = new MembershipId(userId, teamId);
        MembershipModel membershipModel = membershipRepository.findByMembershipId(membershipId);
        if (membershipModel == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        membershipValidations.validations(membershipModel);
        return membershipModel.getRoleId();
    }
}
