package com.simian.simianwork.infrastructure.repositoy;

import com.simian.simianwork.entity.Access;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessRepository extends JpaRepository<Access, Long> {
}
