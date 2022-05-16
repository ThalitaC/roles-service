package com.thalita.roles.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CheckMembershipServiceTest {

    @Autowired
    private CheckMembershipService checkMembershipService;

    public String teamId = "7676a4bf-adfe-415c-941b-1739af07039b";
    public String leadId = "b12fa35a-9c4c-4bf9-8f32-27cf03a1f190";
    public String userId = "371d2ee8-cdf4-48cf-9ddb-04798b79ad9e";


    @Test
    @DisplayName("Should return true when user is a team leader")
    public void userIsTeamLead() {
        Boolean result = checkMembershipService.isMember(teamId, leadId);
        assert result == true;
    }

    @Test
    @DisplayName("Should return true when user is a team member")
    public void userIsTeamMember() {
        Boolean result = checkMembershipService.isMember(teamId, userId);
        assert result == true;
    }

    @Test
    @DisplayName("Should return false when user is not a team member")
    public void userIsNotTeamMember() {
        Boolean result = checkMembershipService.isMember(teamId, "not a user");
        assert result == false;
    }

    @Test
    @DisplayName("Should return false when team does not exist")
    public void teamDoesNotExist() {
        Boolean result = checkMembershipService.isMember("not a team", userId);
        assert result == false;
    }

    @Test
    @DisplayName("Should return false when team is empty")
    public void teamIsEmpty() {
        Boolean result = checkMembershipService.isMember("", userId);
        assert result == false;
    }

    @Test
    @DisplayName("Should return false when user is empty")
    public void userIsEmpty() {
        Boolean result = checkMembershipService.isMember(teamId, "");
        assert result == false;
    }

}

