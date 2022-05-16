package com.thalita.roles.service;

import com.thalita.roles.model.RoleModel;
import com.thalita.roles.repository.RoleRepository;
import org.junit.jupiter.api.*;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class RoleServiceTest {

    @InjectMocks
    private RoleService roleService;

    @Mock
    private RoleRepository roleRepository;

    public RoleServiceTest(){
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("Should create a new role")
    void createRole() {
        RoleModel roleModel = new RoleModel();
        roleModel.setName("this is a role");

        when(roleRepository.save(roleModel)).thenReturn(roleModel);

        RoleModel role = roleService.saveOrUpdate(roleModel);

        assertEquals("this is a role", role.getName());
    }

    @Test
    @DisplayName("When role name is empty, should throw an exception")
    void createRoleWithEmptyName() {
        HttpClientErrorException exception = Assertions.assertThrows(HttpClientErrorException.class, () -> {
            RoleModel roleModel = new RoleModel();
            roleModel.setName("");

            roleService.saveOrUpdate(roleModel);
        });

        assertEquals(HttpClientErrorException.class, exception.getClass());
    }

    @Test
    @DisplayName("When name is null, should throw an exception")
    void createRoleWithNullName() {
        NullPointerException exception = Assertions.assertThrows(NullPointerException.class, () -> {
            RoleModel roleModel = new RoleModel();
            roleModel.setName(null);

            roleService.saveOrUpdate(roleModel);
        });

        assertEquals(NullPointerException.class, exception.getClass());
    }

    @Test
    @DisplayName("Should get all roles")
    void getAllRoles() {
        RoleModel roleModel = new RoleModel();
        roleModel.setName("this is a role");

        when(roleRepository.findAll()).thenReturn(List.of(roleModel));

        List<RoleModel> roles = roleService.getAllRoles();

        assertEquals(1, roles.size());
    }

    @Test
    @DisplayName("Should get a role by id")
    void getRoleById() {
        RoleModel roleModel = new RoleModel();
        roleModel.setId(1L);
        roleModel.setName("this is a role");

        when(roleRepository.findById(1L)).thenReturn(Optional.of(roleModel));

        Optional<RoleModel> role = roleService.getRoleById(1L);

        assertEquals(1L, role.get().getId());
    }

    @Test
    @DisplayName("When id did not exist, should throw an exception")
    void getRoleByIdWithIdNotExist() {
        HttpClientErrorException exception = Assertions.assertThrows(HttpClientErrorException.class, () -> {
            roleService.getRoleById(1L);
        });

        assertEquals(HttpClientErrorException.class, exception.getClass());
    }

    @Test
    @DisplayName("Deleting non existent role should throw an exception")
    void deleteRoleByIdWithIdNotExist() {
        HttpClientErrorException exception = Assertions.assertThrows(HttpClientErrorException.class, () -> {
            roleService.deleteRoleById(1L);
        });

        assertEquals(HttpClientErrorException.class, exception.getClass());
    }


}