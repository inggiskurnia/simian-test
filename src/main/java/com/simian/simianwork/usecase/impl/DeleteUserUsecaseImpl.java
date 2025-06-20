package com.simian.simianwork.usecase.impl;

import com.simian.simianwork.entity.User;
import com.simian.simianwork.entity.UserRole;
import com.simian.simianwork.infrastructure.repositoy.UserRepository;
import com.simian.simianwork.infrastructure.repositoy.UserRoleRepository;
import com.simian.simianwork.usecase.DeleteUserUsecase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteUserUsecaseImpl implements DeleteUserUsecase {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    public DeleteUserUsecaseImpl(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Transactional
    @Override
    public void deleteUserById(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found !"));

        userRoleRepository.deleteByUserId(userId);
        userRepository.deleteById(userId);
    }

    @Transactional
    @Override
    public void deleteUserRoleByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found !"));

        userRoleRepository.deleteByUserId(userId);
    }

    @Transactional
    @Override
    public void deleteUserRoleById(Long userRoleId) {
        UserRole userRole = userRoleRepository.findById(userRoleId).orElseThrow(()-> new RuntimeException("User Role not found !"));

        userRoleRepository.deleteById(userRoleId);

    }
}
