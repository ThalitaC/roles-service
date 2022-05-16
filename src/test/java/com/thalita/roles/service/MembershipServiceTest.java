package com.thalita.roles.service;

import com.thalita.roles.model.MembershipId;
import com.thalita.roles.model.MembershipModel;
import com.thalita.roles.repository.MembershipRepository;
import com.thalita.roles.service.validations.MembershipValidations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
class MembershipServiceTest {

    @InjectMocks
    private MembershipService membershipService;

    @Mock
    private MembershipRepository membershipRepository;

    @Mock
    private MembershipValidations membershipValidations;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private final String validUser = "371d2ee8-cdf4-48cf-9ddb-04798b79ad9e";
    private final String validTeam = "7676a4bf-adfe-415c-941b-1739af07039b";

    private final String invalidUser = "invalid";

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
    @DisplayName("Assign a role to a membership")
    void assignRoleToMembership() {
        MembershipModel membershipModel = newMembershipModel();

        when(membershipRepository.save(membershipModel)).thenReturn(membershipModel);

        MembershipModel result = membershipService.assignRoleToMembership(membershipModel);

        assert(result.getMembershipId().getUserId().equals(validUser));
    }

   @Test
    @DisplayName("Find memberships by roleId")
    void findMembershipsByRoleId() {
        MembershipModel membershipModel = newMembershipModel();

        when(membershipRepository.findByRoleId(1L)).thenReturn(List.of(membershipModel));

        List<MembershipModel> result = membershipService.findByRoleId(1L);

        assert(result.get(0).getMembershipId().getUserId().equals(validUser));
    }

    @Test
    @DisplayName("Find a role by userId and teamId")
    void findMembershipByUserIdAndTeamId() {
        MembershipModel membershipModel = newMembershipModel();

        membershipRepository.save(membershipModel);

        when(membershipRepository.findByMembershipId(membershipModel.getMembershipId())).thenReturn(membershipModel);

        Long result = membershipService.findByMembershipId(validUser, validTeam);

        assert (result == 1L);
    }

    @Test
    @DisplayName("Should throw an exception when membership is not found")
    void membershipNotFound() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> {
            membershipService.findByMembershipId(invalidUser, validTeam);
        });
    }

}