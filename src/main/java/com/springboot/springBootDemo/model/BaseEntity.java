package com.springboot.springBootDemo.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

import lombok.Data;

@Data
@MappedSuperclass
public class BaseEntity {
	private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}
