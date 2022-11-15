package com.fond.app.repositories;

import com.fond.app.dto.ProjectDto;
import com.fond.app.models.CV;
import com.fond.app.models.Project;
import com.fond.app.models.Status;
import com.fond.app.models.User;
import org.hibernate.sql.Update;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import javax.transaction.Transactional;
import java.util.List;

public interface ProjectsRepository extends JpaRepository<Project, Long>  {
    @Query("select p from Project p where p.status=?1")
    Page<Project> findByStatusForPage(Status status,Pageable pageable);
    @Query("select p from Project p where p.user.id=:userId")
    Page<Project> findByUserForPage(@Param("userId") long userId,Pageable pageable);

    @Query("SELECT p FROM Project p WHERE p.user=:user")
    Project findProjectByUser(@Param("user") User user);

    @Query("SELECT p FROM Project p WHERE p.user.id=:userId and p.id=:projectId")
    Project getProjectOfUser(@Param("userId") long userId,@Param("projectId") long projectId);

    @Transactional
    @Modifying
    @Query("delete from Project p where p.id=:projectId and p.user.id=:userId")
    void deleteProject(@Param("userId") long userId,@Param("projectId") long projectId);

    @Query("SELECT p FROM Project p where p.mainDeclaration.id=:main_id and p.status='VERIFY'")
    Page<ProjectDto> findProjectWithMainDeclaration(@Param("main_id") Long main_id,Pageable pageable);

    @Query("SELECT p FROM Project p where p.mainDeclaration.id=:mainDeclaration and p.dopDeclaration.id=:dopDeclaration and p.status='VERIFY'")
    Page<ProjectDto> findProjectWithMainDeclarationAndDopDeclaration(@Param("mainDeclaration") Long mainDeclaration, @Param("dopDeclaration") Long dopDeclaration,Pageable pageable);

}
