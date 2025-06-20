package com.simian.simianwork.infrastructure.controller;

import com.simian.simianwork.common.response.ApiResponse;
import com.simian.simianwork.infrastructure.dto.UserRequestDTO;
import com.simian.simianwork.infrastructure.dto.UserRoleRequestDTO;
import com.simian.simianwork.usecase.AssignUserRoleUsecase;
import com.simian.simianwork.usecase.CreateUserUsecase;
import com.simian.simianwork.usecase.GetUserUseCase;
import com.simian.simianwork.usecase.UpdateUserUsecase;
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

    public UserController(CreateUserUsecase createUserUsecase, AssignUserRoleUsecase assignUserRoleUseCase, GetUserUseCase getUserUseCase, UpdateUserUsecase updateUserUsecase) {
        this.createUserUsecase = createUserUsecase;
        this.assignUserRoleUseCase = assignUserRoleUseCase;
        this.getUserUseCase = getUserUseCase;
        this.updateUserUsecase = updateUserUsecase;
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
}
