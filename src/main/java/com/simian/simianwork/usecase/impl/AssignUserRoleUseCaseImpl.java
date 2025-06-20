package com.simian.simianwork.usecase.impl;

import com.simian.simianwork.entity.Role;
import com.simian.simianwork.entity.User;
import com.simian.simianwork.entity.UserRole;
import com.simian.simianwork.infrastructure.dto.UserRoleRequestDTO;
import com.simian.simianwork.infrastructure.dto.UserRoleResponseDTO;
import com.simian.simianwork.infrastructure.repositoy.RoleRepository;
import com.simian.simianwork.infrastructure.repositoy.UserRepository;
import com.simian.simianwork.infrastructure.repositoy.UserRoleRepository;
import com.simian.simianwork.usecase.AssignUserRoleUseCase;
import org.springframework.stereotype.Service;

@Service
public class AssignUserRoleUseCaseImpl implements AssignUserRoleUseCase {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;

    public AssignUserRoleUseCaseImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserRoleResponseDTO assignUserRole(UserRoleRequestDTO req) {

        User user = userRepository.findById(req.getUserId()).orElseThrow(()-> new RuntimeException("User not found !"));
        Role role = roleRepository.findById(req.getRoleId()).orElseThrow(()-> new RuntimeException("Role not found !"));

        UserRole newUserRole = new UserRole();
        newUserRole.setUser(user);
        newUserRole.setRole(role);

        userRoleRepository.save(newUserRole);

        return new UserRoleResponseDTO(user.getId(), user.getName(), role.getId(), role.getName());
    }
}
