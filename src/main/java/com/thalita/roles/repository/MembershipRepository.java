package com.thalita.roles.repository;

import com.thalita.roles.model.MembershipId;
import com.thalita.roles.model.MembershipModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembershipRepository extends JpaRepository<MembershipModel, MembershipId> {
    List<MembershipModel> findByRoleId(Long roleId);

    MembershipModel findByMembershipId(MembershipId membershipId);
}
