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
@Table(name = "accesses")
@SQLDelete(sql = "UPDATE posts SET deleted_at = now() WHERE id = ?")
@FilterDef(name = "deletedAccessFilter")
@Filter(name = "deletedAccessFilter", condition = "deleted_at IS NULL")
public class Access {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accesses_id_gen")
    @SequenceGenerator(name = "accesses_id_gen", sequenceName = "accesses_id_gen", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

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
