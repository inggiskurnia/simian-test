package com.simian.simianwork.usecase;

import com.simian.simianwork.infrastructure.dto.UserResponseDTO;
import com.simian.simianwork.infrastructure.dto.UserRoleResponseDTO;

import java.util.List;

public interface GetUserUseCase {
    UserResponseDTO getUserById(Long id);
    List<UserRoleResponseDTO> getUserRoleByUserId(Long userId);
}
