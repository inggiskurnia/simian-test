package com.simian.simianwork.usecase;

import com.simian.simianwork.infrastructure.dto.UserRequestDTO;
import com.simian.simianwork.infrastructure.dto.UserResponseDTO;

public interface CreateUserUsecase {

    UserResponseDTO createUser (UserRequestDTO userRequestDTO);
}
