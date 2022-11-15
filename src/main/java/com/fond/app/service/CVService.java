package com.fond.app.service;

import com.fond.app.dto.CVDto;
import com.fond.app.forms.CVForm;
import com.fond.app.models.Project;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CVService {
    void createCV(CVForm form, Project project);

    List<CVDto> getAllCVByProject(long projectId, long userId, Pageable pageable);

    int getTotalPageCVsByProject(long projectId, long userId, Pageable pageable);
}
