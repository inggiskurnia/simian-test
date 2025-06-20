package com.simian.simianwork.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.*;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "user_roles")
@SQLDelete(sql = "UPDATE user_roles SET deleted_at = now() WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_roles_id_gen")
    @SequenceGenerator(name = "user_roles_id_gen", sequenceName = "user_roles_id_gen", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

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
