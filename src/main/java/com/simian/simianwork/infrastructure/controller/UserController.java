package com.simian.simianwork.infrastructure.controller;

import com.simian.simianwork.common.response.ApiResponse;
import com.simian.simianwork.infrastructure.dto.UserRequestDTO;
import com.simian.simianwork.usecase.CreateUserUsecase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final CreateUserUsecase createUserUsecase;

    public UserController(CreateUserUsecase createUserUsecase) {
        this.createUserUsecase = createUserUsecase;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRequestDTO req){
        return ApiResponse.successResponse(HttpStatus.OK.value(), "Create new post success !", createUserUsecase.createUser(req));
    }
}
