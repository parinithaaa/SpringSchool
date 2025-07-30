package com.springboot.springBootDemo.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
	@CreatedDate
	@Column(updatable=false)
	private LocalDateTime createdAt;
	
	@CreatedBy
	@Column(updatable=false)
    private String createdBy;
	
	@LastModifiedDate
	@Column(insertable = false)
    private LocalDateTime updatedAt;
	
	@LastModifiedBy
	@Column(insertable=false)
    private String updatedBy;
}
