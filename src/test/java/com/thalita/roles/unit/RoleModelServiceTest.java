package com.thalita.roles.unit;

import com.thalita.roles.model.RoleModel;
import com.thalita.roles.service.RoleService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
class RoleModelServiceTest {

    @Autowired
    private RoleService roleService;


    @Test
    void getAllRoles() {
        // temp test to check if the service is working
        List<RoleModel> roles = roleService.getAllRoles();
        assert roles.size() == 3;
    }
}