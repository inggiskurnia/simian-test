package com.simian.simianwork.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.SQLDelete;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "role_accesses")
@SQLDelete(sql = "UPDATE posts SET deleted_at = now() WHERE id = ?")
@FilterDef(name = "deletedRoleAccessesFilter")
@Filter(name = "deletedRoleAccessesFilter", condition = "deleted_at IS NULL")
public class RoleAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_accesses_id_gen")
    @SequenceGenerator(name = "role_accesses_id_gen", sequenceName = "role_accesses_id_gen", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "access_id", nullable = false)
    private Access access;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @Column(name = "deleted_at")
    private OffsetDateTime deletedAt;

    @PrePersist
    public void onCreate(){
        if (this.createdAt == null){
            this.createdAt = OffsetDateTime.now();
        }

        this.updatedAt = OffsetDateTime.now();
    }

    @PreUpdate
    public void onUpdate(){
        this.updatedAt = OffsetDateTime.now();
    }

    @PreRemove
    public void onDelete(){
        this.deletedAt = OffsetDateTime.now();
    }
}
