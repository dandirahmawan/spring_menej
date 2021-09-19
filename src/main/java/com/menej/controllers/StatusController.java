package com.menej.controllers;

import com.menej.model.db.StatusModule;
import com.menej.repo.StatusModuleRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class StatusController {
    @Autowired
    StatusModuleRepo smr;

    @PostMapping("/new_status")
    public String newStatus(int projectId, String statusName, String color){
        String strReturn = "";
        List<StatusModule> smList = smr.findByProjectIdAndStatus(projectId, statusName);
        if(smList.size() == 0){
            StatusModule sm = new StatusModule();
            sm.setProjectId(projectId);
            sm.setColor(color);
            sm.setStatus(statusName);
            smr.save(sm);

            List<StatusModule> smListNew = smr.findByProjectIdAndStatus(projectId, statusName);
            ObjectMapper mapper = new ObjectMapper();
            try{
                strReturn = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(smListNew);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            HashMap<String, String> map = new HashMap<>();
            map.put("status", "error");
            map.put("description", "statu is aleready exits");
            strReturn = map.toString();
        }
        return strReturn;
    }

    @PostMapping("/edit_status")
    public Boolean editStatus(int statusId, String statusName, int projectId, String color){
        List<StatusModule> statusModuleList = smr.findByProjectIdAndId(projectId, statusId);
        if(statusModuleList.size() > 0){
            StatusModule statusModule = new StatusModule();
            statusModule.setProjectId(projectId);
            statusModule.setStatus(statusName);
            statusModule.setId(statusId);
            statusModule.setColor(color);
            smr.save(statusModule);
            return true;
        }else{
            return false;
        }
    }

    @PostMapping("/delete_status")
    public Boolean deleteStatus(int projectId, int statusId){
        Boolean isDelete = false;
        int delete = smr.deleteStatus(projectId, statusId);
        if(delete == 1){
            isDelete = true;
        }
        return isDelete;
    }
}
