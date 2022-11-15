package com.fond.app.repositories;

import com.fond.app.models.DopDeclaration;
import com.fond.app.models.MainDeclaration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface DopDeclarationRepository extends JpaRepository<DopDeclaration,Long> {
    List<DopDeclaration> findByMainDeclaration(MainDeclaration mainDeclaration);
    @Transactional
    @Modifying
    @Query("update DopDeclaration d set d.nameDeclaration=?1 where d.mainDeclaration.id = ?2 and d.id = ?3")
    void updateDopDeclaration(String nameDeclaration,Long mainDeclarationId,Long dopDeclarationId);
    @Transactional
    @Modifying
    @Query("delete from DopDeclaration d where d.mainDeclaration.id = ?1 and d.id = ?2")
    void deleteDopDeclaration(Long mainDeclarationId,Long dopDeclarationId);
}
