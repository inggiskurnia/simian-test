package com.simian.simianwork.infrastructure.dto;

import com.simian.simianwork.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleRequestDTO {
    private Long userId;
    private Long roleId;
}
