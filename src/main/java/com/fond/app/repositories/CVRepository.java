package com.fond.app.repositories;

import com.fond.app.models.CV;
import com.fond.app.models.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CVRepository extends JpaRepository<CV, Long> {

    @Query("SELECT c FROM CV c  WHERE c.project.id=:projectId and c.project.user.id=:userId")
    Page<CV> findAllCVByProject(@Param("projectId") long projectId, @Param("userId") long userId, Pageable pageable);

    @Transactional
    void deleteAllByProject_Id(Long projectId);
}
