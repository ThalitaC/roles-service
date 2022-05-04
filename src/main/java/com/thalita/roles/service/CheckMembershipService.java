package com.thalita.roles.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CheckMembershipService {

    public static final String TEAMS_URL = "https://cgjresszgg.execute-api.eu-west-1.amazonaws.com/teams";

    public String getTeam(String id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(TEAMS_URL + "/" + id, String.class);
    }

    public Boolean isMember(String teamId, String userId) {

        String team = getTeam(teamId);
        String[] teamMembers = team.split("[,:\"\t]");
        for (String member : teamMembers) {
            if (member.equals(userId)) {
                return true;
            }
        }
        return false;
    }
}
