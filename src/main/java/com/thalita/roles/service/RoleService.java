package com.thalita.roles.service;

import com.thalita.roles.model.RoleModel;
import com.thalita.roles.repository.RoleRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public List<RoleModel> getAllRoles() {
       return roleRepository.findAll();
    }


    public RoleModel saveOrUpdate(@NotNull RoleModel roleModel) {
        String roleName = roleModel.getName();

        if (roleName.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Role name cannot be empty");
        }

        if (roleRepository.existsByName(roleName)) {
            throw new HttpClientErrorException(HttpStatus.CONFLICT, "Role with name " + roleName + " already exists");
        }

        roleRepository.save(roleModel);
        return roleModel;
    }

    public Optional<RoleModel> getRoleById(long id) {
        if (!roleRepository.findById(id).isPresent()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Role with id " + id + " does not exist");
        }
        return roleRepository.findById(id);
    }

    public void deleteRoleById(long id) {
        if (!roleRepository.existsById(id)) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Role with id " + id + " does not exist");
        }
        roleRepository.deleteById(id);
    }

}
