package com.fond.app.controllers;

import com.fond.app.dto.DopDeclarationDto;
import com.fond.app.models.MainDeclaration;
import com.fond.app.service.DeclarationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DirectionRestController {
    @Autowired
    DeclarationsService declarationsService;
    @PostMapping("/api/getDirection")
    public Map<Long,String> getDirection(@RequestParam("firstDirection") Long firstDirection) {
        MainDeclaration mainDeclaration = declarationsService.getMainDeclaration(firstDirection).getMainDeclaration();
        List<DopDeclarationDto> dopDeclarationDtos = declarationsService.getAllDopDeclarationOfMain(mainDeclaration);
        Map<Long, String> results = new HashMap<>();
        for (DopDeclarationDto dopDeclarationDto : dopDeclarationDtos) {
            results.put(dopDeclarationDto.getId(), dopDeclarationDto.getNameDeclaration());
        }
        return results;
    }
}
