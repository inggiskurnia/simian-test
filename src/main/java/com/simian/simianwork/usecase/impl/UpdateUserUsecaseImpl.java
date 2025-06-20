package com.simian.simianwork.usecase.impl;

import com.simian.simianwork.entity.Role;
import com.simian.simianwork.entity.User;
import com.simian.simianwork.entity.UserRole;
import com.simian.simianwork.infrastructure.dto.UserRequestDTO;
import com.simian.simianwork.infrastructure.dto.UserResponseDTO;
import com.simian.simianwork.infrastructure.dto.UserRoleRequestDTO;
import com.simian.simianwork.infrastructure.repositoy.RoleRepository;
import com.simian.simianwork.infrastructure.repositoy.UserRepository;
import com.simian.simianwork.infrastructure.repositoy.UserRoleRepository;
import com.simian.simianwork.usecase.UpdateUserUsecase;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserUsecaseImpl implements UpdateUserUsecase {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    public UpdateUserUsecaseImpl(UserRepository userRepository, RoleRepository roleRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserResponseDTO updateUserById(Long userId, UserRequestDTO req) {

        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found !"));

        if (req.getEmail() != null){
            user.setEmail(req.getEmail());
        }
        if (req.getName() != null){
            user.setName(req.getName());
        }
        if (req.getPassword() != null){
            user.setPassword(req.getPassword());
        }

        User updatedUser = userRepository.save(user);

        return new UserResponseDTO(updatedUser.getId(), updatedUser.getEmail(), updatedUser.getName());
    }

    @Override
    public UserRoleRequestDTO updateUserRoleById(Long userRoleId, UserRoleRequestDTO req) {

        UserRole userRole = userRoleRepository.findById(userRoleId).orElseThrow(()-> new RuntimeException("User role id not found !"));
        Role role = roleRepository.findById(req.getRoleId()).orElseThrow(()-> new RuntimeException("Role not found !"));
        User user = userRepository.findById(req.getUserId()).orElseThrow(()-> new RuntimeException("User not found !"));


        userRole.setRole(role);
        userRole.setUser(user);
        UserRole newUserRole = userRoleRepository.save(userRole);

        return new UserRoleRequestDTO(newUserRole.getUser().getId(), newUserRole.getRole().getId());
    }
}
