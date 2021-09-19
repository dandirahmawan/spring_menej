package com.menej.service;

import com.menej.model.db.Modul;
import com.menej.model.db.Section;
import com.menej.model.db.SectionModule;
import com.menej.model.view.ViewModule;
import com.menej.repo.ModuleRepo;
import com.menej.repo.SectionModuleRepo;
import com.menej.repo.SectionRepo;
import com.menej.repo.ViewModuleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SectionService {
    @Autowired
    SectionRepo sr;

    @Autowired
    ViewModuleRepo vmr;

    @Autowired
    SectionModuleRepo smr;

    public void insertSection(String sectionName, Integer projectId){
        Section section = new Section();
        section.setSection(sectionName);
        section.setProjectId(projectId);
        sr.save(section);
    }

    public Map<String, Object> fetchData(int projectId){
        List<Section> sections = sr.findByProjectId(projectId);
        int last = sections.size() - 1;
        Section section = sections.get(last);

        Map<String, Object> map = new HashMap<>();
        map.put("sections", sections);
        map.put("section", section);
        return map;
    }

    public Map<String, Object> deleteSection(String id){
        int idInt = Integer.parseInt(id);
        List<ViewModule> viewModuleList = vmr.findBySectionId(idInt);
        Map<String, Object> map = new HashMap<>();

        if(viewModuleList.size() > 0){
            map.put("status", "failed");
            map.put("message", "There is task in this section");
        }else{
            Optional<SectionModule> sectionModule = smr.findById(idInt);
            int idS = sectionModule.map(SectionModule::getId).orElse(0);
            if(idS > 0){
                smr.delete(sectionModule.get());
                map.put("status", "success");
                map.put("message", "Delete section is successfully");
            }
        }
        return map;
    }

    public String renameSection(Integer id, String name){
        Optional<Section> section = sr.findById(id);
        Section sect = section.get();
        sect.setSection(name);
        sr.save(sect);
        return "success";
    }
}
