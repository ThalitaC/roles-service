package com.thalita.roles.controller;

import com.thalita.roles.model.MembershipId;
import com.thalita.roles.model.MembershipModel;
import com.thalita.roles.repository.MembershipRepository;
import com.thalita.roles.service.MembershipService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/memberships")
@Tag(name = "Membership")
public class MembershipController {

    @Autowired
    private  MembershipService membershipService;

    @Autowired
    private MembershipRepository membershipRepository;

    public MembershipController() {
    }

    @PostMapping()
    public ResponseEntity<Object> assignRole(@RequestBody MembershipModel membershipModel) {

        return ResponseEntity.ok(membershipService.assignRoleToMembership(membershipModel));
    }

   @GetMapping()
    public ResponseEntity<Object> getAllMemberships() {

        return ResponseEntity.ok(membershipRepository.findAll());
    }

    @GetMapping("/role/{roleId}")
    public ResponseEntity<Object> getAllMembershipsByRole(@PathVariable("roleId") Long roleId) {

        return ResponseEntity.ok(membershipService.findByRoleId(roleId));
    }

    @GetMapping("{userId}/{teamId}")
    public ResponseEntity<Object> getAllMembershipsByMembership(@PathVariable("userId") String userId, @PathVariable("teamId") String teamId) {

        return ResponseEntity.ok(membershipService.findByMembershipId(userId, teamId));
    }
}
