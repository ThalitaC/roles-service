package com.thalita.roles.service;

import com.thalita.roles.model.dto.TeamDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CheckMembershipService {

    public static final String TEAMS_URL = "https://cgjresszgg.execute-api.eu-west-1.amazonaws.com/teams";

    public TeamDTO getTeam(String id) {
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(TEAMS_URL + "/" + id, TeamDTO.class);
    }

    public Boolean isTeamIdValid(String id) {
        if (id.equals("")) {
            return false;
        }

        return true;
    }


    public Boolean isMember(@NotNull String teamId, @NotNull String userId) {

        if (!isTeamIdValid(teamId)) {
            return false;
        }
        TeamDTO team = getTeam(teamId);

        if (team == null) {
            return false;
        }


        String teamLeaderId = team.getTeamLeadId();
        List<String> membersId = team.getTeamMemberIds();

        return userId.equals(teamLeaderId) || membersId.contains(userId);
    }
}
