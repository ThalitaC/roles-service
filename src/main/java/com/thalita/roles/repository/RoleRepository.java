package com.thalita.roles.repository;

import com.thalita.roles.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, Long>, JpaSpecificationExecutor<RoleModel> {
}