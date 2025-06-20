package com.simian.simianwork.infrastructure.controller;

import com.simian.simianwork.common.response.ApiResponse;
import com.simian.simianwork.infrastructure.dto.UserRequestDTO;
import com.simian.simianwork.infrastructure.dto.UserRoleRequestDTO;
import com.simian.simianwork.usecase.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final CreateUserUsecase createUserUsecase;
    private final AssignUserRoleUsecase assignUserRoleUseCase;
    private final GetUserUseCase getUserUseCase;
    private final UpdateUserUsecase updateUserUsecase;
    private final DeleteUserUsecase deleteUserUsecase;

    public UserController(CreateUserUsecase createUserUsecase, AssignUserRoleUsecase assignUserRoleUseCase, GetUserUseCase getUserUseCase, UpdateUserUsecase updateUserUsecase, DeleteUserUsecase deleteUserUsecase) {
        this.createUserUsecase = createUserUsecase;
        this.assignUserRoleUseCase = assignUserRoleUseCase;
        this.getUserUseCase = getUserUseCase;
        this.updateUserUsecase = updateUserUsecase;
        this.deleteUserUsecase = deleteUserUsecase;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId){
        return ApiResponse.successResponse(HttpStatus.OK.value(), "Get user success !", getUserUseCase.getUserById(userId));
    }

    @GetMapping("/role/{userId}")
    public ResponseEntity<?> getUserRolesById(@PathVariable Long userId){
        return ApiResponse.successResponse(HttpStatus.OK.value(), "Get user roles success !", getUserUseCase.getUserRoleByUserId(userId));
    }
    
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUserById(@PathVariable Long userId, @RequestBody UserRequestDTO req){
        return ApiResponse.successResponse(HttpStatus.OK.value(), "Update user roles success !", updateUserUsecase.updateUserById(userId, req));
    }

    @PutMapping("/role/{userRoleId}")
    public ResponseEntity<?> updateUseRoleById(@PathVariable Long userRoleId, @RequestBody UserRoleRequestDTO req){
        return ApiResponse.successResponse(HttpStatus.OK.value(), "Update user roles success !", updateUserUsecase.updateUserRoleById(userRoleId, req));
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRequestDTO req){
        return ApiResponse.successResponse(HttpStatus.OK.value(), "Create new user success !", createUserUsecase.createUser(req));
    }

    @PostMapping("/assign")
    public ResponseEntity<?> assignUserRole(@RequestBody UserRoleRequestDTO req){
        return ApiResponse.successResponse(HttpStatus.OK.value(), "Assign new user success !", assignUserRoleUseCase.assignUserRole(req));
    }

    @DeleteMapping("/role/{userRoleId}")
    public ResponseEntity<?> deleteUserByUserRoleId(@PathVariable Long userRoleId){
        deleteUserUsecase.deleteUserRoleById(userRoleId);

        return ApiResponse.successResponse(HttpStatus.OK.value(), "Delete user role by userRoleId success", null);
    }

    @DeleteMapping("/user-role/{userId}")
    public ResponseEntity<?> deleteUserByUserId(@PathVariable Long userId){

        deleteUserUsecase.deleteUserRoleByUserId(userId);

        return ApiResponse.successResponse(HttpStatus.OK.value(),"Delete user role by userId success", null);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long userId){
        deleteUserUsecase.deleteUserById(userId);

        return ApiResponse.successResponse(HttpStatus.OK.value(), "Delete user role by userId success", null);
    }
}
