package com.simian.simianwork.usecase;

import com.simian.simianwork.infrastructure.dto.UserRoleRequestDTO;
import com.simian.simianwork.infrastructure.dto.UserRoleResponseDTO;

public interface AssignUserRoleUseCase {

    UserRoleResponseDTO assignUserRole(UserRoleRequestDTO req);
}
