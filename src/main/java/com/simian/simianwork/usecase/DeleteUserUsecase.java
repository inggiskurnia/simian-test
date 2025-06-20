package com.simian.simianwork.usecase;

public interface DeleteUserUsecase {
    void deleteUserById(Long userId);
    void deleteUserRoleByUserId(Long userId);
    void deleteUserRoleById(Long userRoleId);
}
