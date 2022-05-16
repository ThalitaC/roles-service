package com.thalita.roles.controller;

import com.thalita.roles.RolesApplication;
import com.thalita.roles.model.MembershipId;
import com.thalita.roles.model.MembershipModel;
import com.thalita.roles.model.RoleModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MembershipControllerTest {

    @BeforeAll
    void setUp() {
        RolesApplication.main(new String[]{});
    }

    private final String URL = "http://localhost:8080/memberships";

    private final String validUser = "371d2ee8-cdf4-48cf-9ddb-04798b79ad9e";
    private final String validTeam = "7676a4bf-adfe-415c-941b-1739af07039b";

    private final String invalidUser = "invalid";

    TestRestTemplate testTemplate = new TestRestTemplate();

    public MembershipModel newMembershipModel() {
        MembershipId membershipId = new MembershipId();
        membershipId.setUserId(validUser);
        membershipId.setTeamId(validTeam);

        MembershipModel membershipModel = new MembershipModel();
        membershipModel.setMembershipId(membershipId);
        membershipModel.setRoleId(1L);

        return membershipModel;
    }

    @Test
    @DisplayName("Should create a membership")
    void createMembership() {

        MembershipModel membershipModel = newMembershipModel();


        ResponseEntity<MembershipModel> response = testTemplate.postForEntity(URL, membershipModel, MembershipModel.class);

        assertEquals(membershipModel.getMembershipId(), response.getBody().getMembershipId());
    }

    @Test
    @DisplayName("Should throw an exception when trying to create a membership with an invalid user")
    void createMembershipWithInvalidUser() {
        MembershipModel membershipModel = newMembershipModel();
        membershipModel.getMembershipId().setUserId(invalidUser);

        ResponseEntity<MembershipModel> response = testTemplate.postForEntity(URL, membershipModel, MembershipModel.class);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Get all memberships")
    void getAllMemberships() {
        ResponseEntity<List<MembershipModel>> response = testTemplate.exchange(URL,
                HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<>() {});
                assertEquals(200, response.getStatusCodeValue());
    }
    @Test
    @DisplayName("Get all memberships for a role")
    void getAllMembershipsForRole() {
        ResponseEntity<List<MembershipModel>> response = testTemplate.exchange(URL + "/role/1",
                HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<>() {});
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Get a role for a membership")
    void getRoleForMembership() {
        MembershipModel membershipModel = newMembershipModel();
        testTemplate.postForEntity(URL, membershipModel, MembershipModel.class);

        ResponseEntity<Long> response = testTemplate.exchange(URL + "/" + validUser + "/" + validTeam, HttpMethod.GET, HttpEntity.EMPTY, Long.class);

        assertEquals(1, response.getBody());
    }
}