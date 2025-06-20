package com.simian.simianwork.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleResponseDTO {
    private Long userId;
    private String email;
    private Long roleId;
    private String roleName;

}
