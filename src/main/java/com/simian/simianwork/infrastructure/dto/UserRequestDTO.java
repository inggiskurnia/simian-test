package com.simian.simianwork.infrastructure.dto;

import com.simian.simianwork.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    @Email
    @NotNull
    private String email;

    @NotNull
    private String name;

    @NotNull
    private String password;

    public User toEntity(){
        User newUser = new User();
        newUser.setEmail(this.email);
        newUser.setName(this.name);
        newUser.setPassword(this.password);
        return newUser;
    }

}
