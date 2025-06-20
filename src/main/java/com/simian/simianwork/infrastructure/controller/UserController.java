package com.simian.simianwork.infrastructure.controller;

import com.simian.simianwork.common.response.ApiResponse;
import com.simian.simianwork.infrastructure.dto.UserRequestDTO;
import com.simian.simianwork.infrastructure.dto.UserRoleRequestDTO;
import com.simian.simianwork.usecase.AssignUserRoleUseCase;
import com.simian.simianwork.usecase.CreateUserUsecase;
import com.simian.simianwork.usecase.GetUserUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final CreateUserUsecase createUserUsecase;
    private final AssignUserRoleUseCase assignUserRoleUseCase;
    private final GetUserUseCase getUserUseCase;

    public UserController(CreateUserUsecase createUserUsecase, AssignUserRoleUseCase assignUserRoleUseCase, GetUserUseCase getUserUseCase) {
        this.createUserUsecase = createUserUsecase;
        this.assignUserRoleUseCase = assignUserRoleUseCase;
        this.getUserUseCase = getUserUseCase;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId){
        return ApiResponse.successResponse(HttpStatus.OK.value(), "Get user success !", getUserUseCase.getUserById(userId));
    }

    @GetMapping("/roles/{userId}")
    public ResponseEntity<?> getUserRolesById(@PathVariable Long userId){
        return ApiResponse.successResponse(HttpStatus.OK.value(), "Get user roles success !", getUserUseCase.getUserRoleByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRequestDTO req){
        return ApiResponse.successResponse(HttpStatus.OK.value(), "Create new user success !", createUserUsecase.createUser(req));
    }

    @PostMapping("/assign")
    public ResponseEntity<?> assignUserRole(@RequestBody UserRoleRequestDTO req){
        return ApiResponse.successResponse(HttpStatus.OK.value(), "Assign new user success !", assignUserRoleUseCase.assignUserRole(req));
    }
}
