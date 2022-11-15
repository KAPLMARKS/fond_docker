package com.fond.app.repositories;

import com.fond.app.models.MainDeclaration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface MainDeclarationRepository extends JpaRepository<MainDeclaration,Long> {
    @Transactional
    @Modifying
    @Query("update MainDeclaration d set d.nameDeclaration=?1 where d.id = ?2")
    void updateMainDeclaration(String nameDeclaration,Long mainDeclarationId);

}
