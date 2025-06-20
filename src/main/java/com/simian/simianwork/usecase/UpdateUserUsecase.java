package com.simian.simianwork.usecase;

import com.simian.simianwork.infrastructure.dto.UserRequestDTO;
import com.simian.simianwork.infrastructure.dto.UserResponseDTO;
import com.simian.simianwork.infrastructure.dto.UserRoleRequestDTO;

public interface UpdateUserUsecase {

    UserResponseDTO updateUserById(Long userId, UserRequestDTO req);

    UserRoleRequestDTO updateUserRoleById(Long userRoleId, UserRoleRequestDTO req);
}
