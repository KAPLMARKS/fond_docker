package com.fond.app.service;

import com.fond.app.dto.CVDto;
import com.fond.app.forms.CVForm;
import com.fond.app.models.CV;
import com.fond.app.models.Project;
import com.fond.app.repositories.CVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CVServiceImpl implements CVService {
    @Autowired
    private CVRepository cvRepository;

    @Override
    public void createCV(CVForm form, Project project) {
        CV cv = CV.builder()
                .name(form.getName())
                .email(form.getEmail())
                .phone(form.getPhone())
                .aboutMe(form.getAboutMe())
                .project(project)
                .inn(form.getInn())
                .build();
        cvRepository.save(cv);
    }
    @Override
    public List<CVDto> getAllCVByProject(long projectId, long userId, Pageable pageable) {
        Page<CV> cvs = cvRepository.findAllCVByProject(projectId,userId,pageable);
        return cvs.get().map(CVDto::from).collect(Collectors.toList());
    }
    @Override
    public int getTotalPageCVsByProject(long projectId, long userId, Pageable pageable){
        Page<CV> cvs = cvRepository.findAllCVByProject(projectId,userId,pageable);
        return cvs.getTotalPages();
    }
}
