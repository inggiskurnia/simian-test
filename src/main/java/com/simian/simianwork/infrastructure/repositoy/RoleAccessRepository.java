package com.simian.simianwork.infrastructure.repositoy;

import com.simian.simianwork.entity.RoleAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleAccessRepository extends JpaRepository<RoleAccess, Long> {
}
