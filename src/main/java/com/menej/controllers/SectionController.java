package com.menej.controllers;

import com.menej.model.db.Section;
import com.menej.repo.SectionRepo;
import com.menej.service.SectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/section")
@Slf4j
public class SectionController {

    @Autowired
    SectionService ss;

    @PostMapping
    public Map<String, Object> insert(@RequestHeader HttpHeaders reqHeader, String section, Integer projectId){
        ss.insertSection(section, projectId);
        return ss.fetchData(projectId);
    }

    @PostMapping("/delete")
    public Map<String, Object> deleteSection(String id){
        return ss.deleteSection(id);
    }

    @PostMapping("/rename")
    public String renameSection(Integer id, String name){
        return ss.renameSection(id, name);
    }
}
