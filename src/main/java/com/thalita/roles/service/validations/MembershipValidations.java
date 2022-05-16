package com.thalita.roles.service.validations;

import com.thalita.roles.model.MembershipModel;
import com.thalita.roles.service.CheckMembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class MembershipValidations {

    @Autowired
    CheckMembershipService checkMembershipService;

    public MembershipValidations() {
    }

    public Boolean validations(MembershipModel membershipModel) {
        if (!checkMembershipService.isMember(membershipModel.getMembershipId().getTeamId(), membershipModel.getMembershipId().getUserId())) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"User is not member of team");
        }
        return true;
    }

}
