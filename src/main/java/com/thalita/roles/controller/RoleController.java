package com.thalita.roles.controller;


import com.thalita.roles.model.RoleModel;
import com.thalita.roles.repository.RoleRepository;
import com.thalita.roles.service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@Tag(name = "roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRepository roleRepository;

    public RoleController() {
    }

    @PostMapping()
    public ResponseEntity<RoleModel> createOrUpdateRole(@RequestBody RoleModel roleModel) {

        return ResponseEntity.ok(roleService.saveOrUpdate(roleModel));
    }

    @GetMapping()
    public ResponseEntity<List<RoleModel>> getAllRoles() {
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleModel> getRoleById(@PathVariable("id") Long id) {
        return ResponseEntity.of(roleService.getRoleById(id));
    }

}
