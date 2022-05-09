package com.thalita.roles.service;

import com.thalita.roles.model.RoleModel;
import com.thalita.roles.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;


    public List<RoleModel> getAllRoles() {
       return roleRepository.findAll();
    }
}
