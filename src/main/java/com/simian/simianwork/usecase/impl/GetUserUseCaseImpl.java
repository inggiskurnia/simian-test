package com.simian.simianwork.usecase.impl;

import com.simian.simianwork.entity.User;
import com.simian.simianwork.entity.UserRole;
import com.simian.simianwork.infrastructure.dto.UserResponseDTO;
import com.simian.simianwork.infrastructure.dto.UserRoleResponseDTO;
import com.simian.simianwork.infrastructure.repositoy.UserRepository;
import com.simian.simianwork.infrastructure.repositoy.UserRoleRepository;
import com.simian.simianwork.usecase.GetUserUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUserUseCaseImpl implements GetUserUseCase {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    public GetUserUseCaseImpl(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserResponseDTO getUserById(Long id) {

        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found !"));

        return new UserResponseDTO(user.getId(), user.getEmail(), user.getName());
    }

    @Override
    public List<UserRoleResponseDTO> getUserRoleByUserId(Long userId) {
        List<UserRole> userRoles = userRoleRepository.findByUserId(userId);

        return userRoles.stream()
                .map(userRole -> new UserRoleResponseDTO(
                        userRole.getUser().getId(),
                        userRole.getUser().getName(),
                        userRole.getRole().getId(),
                        userRole.getRole().getName()
                ))
                .toList();
    }
}
