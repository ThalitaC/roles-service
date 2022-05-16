package com.thalita.roles.model;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "membership")
public class MembershipModel {
    @EmbeddedId
    private MembershipId membershipId;

    @Column(name = "role")
    @ColumnDefault("1")
    private Long roleId;

    public MembershipModel(MembershipId membershipId, long roleId) {
    }

    public MembershipModel() {

    }


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public MembershipId getMembershipId() {
        return membershipId;
    }

    public MembershipModel setMembershipId(MembershipId membershipId) {
        this.membershipId = membershipId;
        return this;
    }

}