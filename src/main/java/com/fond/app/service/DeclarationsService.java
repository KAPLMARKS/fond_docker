package com.fond.app.service;

import com.fond.app.dto.DopDeclarationDto;
import com.fond.app.dto.MainDeclarationDto;
import com.fond.app.forms.*;
import com.fond.app.models.MainDeclaration;

import java.util.List;

public interface DeclarationsService {
    void CreateMainDeclaration(CreateMainDeclarationForm form);

    void CreateDopDeclaration(CreateDopDeclarationForm form, MainDeclaration mainDeclaration);

    MainDeclarationDto getMainDeclaration(Long mainDeclarationId);

    DopDeclarationDto getDopDeclaration(Long dopDeclarationId);

    List<MainDeclarationDto> getAllMainDeclaration();

    List<DopDeclarationDto> getAllDopDeclarationOfMain(MainDeclaration mainDeclaration);

    void deleteMainDeclaration(Long mainDeclarationId);

    void deleteDopDeclaration(DeleteDopDeclarationForm dopDeclarationId);

    void updateMainDeclaration(UpdateMainDeclarationForm form);

    void updateDopDeclaration(UpdateDopDeclarationForm form);
}
