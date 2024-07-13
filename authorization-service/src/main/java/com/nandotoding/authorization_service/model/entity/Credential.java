package com.nandotoding.authorization_service.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "credentials")
@Data
public class Credential {
    @Id
    private String id;
    private String username;
    private String password;
    private boolean isAdmin;
    private boolean isDeleted;
}
