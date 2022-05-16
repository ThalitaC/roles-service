package com.thalita.roles.controller;

import com.thalita.roles.RolesApplication;
import com.thalita.roles.error.ErrorResponse;
import com.thalita.roles.model.RoleModel;
import org.junit.jupiter.api.*;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;



import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RoleControllerTest {
    TestRestTemplate testTemplate = new TestRestTemplate();

    @BeforeAll
    void setUp() {
        RolesApplication.main(new String[]{});
    }

    @Test
    @DisplayName("Should create role")
    void createRole() {

        RoleModel roleModel = new RoleModel();
        roleModel.setName("newRole");


        ResponseEntity<RoleModel> response = testTemplate.postForEntity("http://localhost:8080/roles", roleModel, RoleModel.class);

        Assertions.assertEquals(4L, response.getBody().getId());
    }

    @Test
    @DisplayName("Should get all roles")
    void getAllRoles() {

        ResponseEntity<List<RoleModel>> response = testTemplate.exchange("http://localhost:8080/roles",
                HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<>() {});


        Assertions.assertEquals(3, response.getBody().size());
    }

    @Test
    @DisplayName("Should get role by id")
    void getRoleById() {

        ResponseEntity<RoleModel> response = testTemplate.exchange("http://localhost:8080/roles/1",
                HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<>() {});

        Assertions.assertEquals(1, response.getBody().getId());
    }

    @Test
    @DisplayName("Should throw exception when role is not found")
    void getRoleByIdNotFound() {

        ResponseEntity<ErrorResponse> response = testTemplate.exchange("http://localhost:8080/roles/5",
                HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<>() {});

        Assertions.assertEquals("404 NOT_FOUND", response.getBody().getCode());
        Assertions.assertEquals("404 Role with id 5 does not exist", response.getBody().getMessage());
    }

}