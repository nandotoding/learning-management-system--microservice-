package com.nandotoding.authorization_service.repository;

import com.nandotoding.authorization_service.model.entity.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, String> {
    @Query(value = "SELECT * FROM credentials c WHERE c.username = :username", nativeQuery = true)
    public Credential getCredentialByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO credentials (id, username, password, is_admin, is_deleted) " +
            "VALUES (:id, :username, :password, :isAdmin, :isDeleted)", nativeQuery = true)
    public void addCredential(String id, String username, String password, boolean isAdmin, boolean isDeleted);
}
