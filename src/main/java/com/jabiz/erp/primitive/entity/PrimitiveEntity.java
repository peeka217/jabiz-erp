package com.jabiz.erp.primitive.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(value = AuditingEntityListener.class)
@Data
@MappedSuperclass
public class PrimitiveEntity {

    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;
    @Column(updatable = false)
    @CreatedBy
    private Long createdBy;

    @Column(insertable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @Column(insertable = false)
    @LastModifiedBy
    private Long updatedBy;


}
