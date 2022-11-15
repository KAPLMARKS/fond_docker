package com.fond.app.service;

import com.fond.app.dto.DopDeclarationDto;
import com.fond.app.dto.MainDeclarationDto;
import com.fond.app.forms.*;
import com.fond.app.models.DopDeclaration;
import com.fond.app.models.MainDeclaration;
import com.fond.app.repositories.DopDeclarationRepository;
import com.fond.app.repositories.MainDeclarationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeclarationsServiceImpl implements DeclarationsService{
    @Autowired
    private MainDeclarationRepository mainDeclarationRepository;
    @Autowired
    private DopDeclarationRepository dopDeclarationRepository;
    @Override
    public void CreateMainDeclaration(CreateMainDeclarationForm form){
        MainDeclaration mainDeclaration=MainDeclaration.builder()
                .nameDeclaration(form.getMainDeclaration())
                .build();
        mainDeclarationRepository.save(mainDeclaration);
    }
    @Override
    public void CreateDopDeclaration(CreateDopDeclarationForm form,MainDeclaration mainDeclaration){
        DopDeclaration dopDeclaration=DopDeclaration.builder()
                .nameDeclaration(form.getDopDeclaration())
                .mainDeclaration(mainDeclaration)
                .build();
        dopDeclarationRepository.save(dopDeclaration);
    }

    @Override
    public MainDeclarationDto getMainDeclaration(Long mainDeclarationId){
        return MainDeclarationDto.from(mainDeclarationRepository.getReferenceById(mainDeclarationId));
    }
    @Override
    public DopDeclarationDto getDopDeclaration(Long dopDeclarationId){
        return DopDeclarationDto.from(dopDeclarationRepository.getReferenceById(dopDeclarationId));
    }
    @Override
    public List<MainDeclarationDto> getAllMainDeclaration(){
        List<MainDeclaration> mainDeclarations=mainDeclarationRepository.findAll();
        return MainDeclarationDto.from(mainDeclarations);
    }
    @Override
    public List<DopDeclarationDto> getAllDopDeclarationOfMain(MainDeclaration mainDeclaration){
        List<DopDeclaration> dopDeclarations=dopDeclarationRepository.findByMainDeclaration(mainDeclaration);
        return DopDeclarationDto.from(dopDeclarations);
    }
    @Override
    public void deleteMainDeclaration(Long mainDeclarationId){
        mainDeclarationRepository.deleteById(mainDeclarationId);
    }
    @Override
    public void deleteDopDeclaration(DeleteDopDeclarationForm form){
        dopDeclarationRepository.deleteDopDeclaration(form.getMainDeclaration(),form.getDopDeclaration());
    }
    @Override
    public void updateMainDeclaration(UpdateMainDeclarationForm form){
        mainDeclarationRepository.updateMainDeclaration(form.getNameDeclaration(), form.getMainDeclaration());
    }
    @Override
    public void updateDopDeclaration(UpdateDopDeclarationForm form){
        dopDeclarationRepository.updateDopDeclaration(form.getNameDeclaration(),form.getMainDeclaration(),form.getDopDeclaration());
    }

}
