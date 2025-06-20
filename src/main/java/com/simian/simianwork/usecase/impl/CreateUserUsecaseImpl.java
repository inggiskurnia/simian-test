package com.simian.simianwork.usecase.impl;

import com.simian.simianwork.entity.Role;
import com.simian.simianwork.entity.User;
import com.simian.simianwork.infrastructure.dto.UserRequestDTO;
import com.simian.simianwork.infrastructure.dto.UserResponseDTO;
import com.simian.simianwork.infrastructure.repositoy.RoleRepository;
import com.simian.simianwork.infrastructure.repositoy.UserRepository;
import com.simian.simianwork.usecase.CreateUserUsecase;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateUserUsecaseImpl implements CreateUserUsecase {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public CreateUserUsecaseImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {

        Optional<User> existingUser = userRepository.findByEmail(userRequestDTO.getEmail());

        if (existingUser.isPresent()){
            throw  new DuplicateKeyException("email already exist");
        }

        User newUser = userRequestDTO.toEntity();
        User savedUser = userRepository.save(newUser);

        return new UserResponseDTO(savedUser.getId(), savedUser.getEmail(), savedUser.getName());
    }
}
