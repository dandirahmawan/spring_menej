package com.menej.controllers;

import com.menej.model.db.UserPrivacyTab;
import com.menej.model.view.DataProjectTeam;
import com.menej.model.db.Tab;
import com.menej.repo.DataProjectTeamRepo;
import com.menej.repo.TabRepo;
import com.menej.repo.UserPrivacyTabRepo;
import com.menej.service.UserPrivacyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserPrivacyController {
    @Autowired
    UserPrivacyService ups;

    @Autowired
    UserPrivacyTabRepo upr;

    @Autowired
    TabRepo tr;

    @Autowired
    DataProjectTeamRepo dptr;

    @PostMapping("/get_user_privacy")
    public String getUserPrivacy(int userId, String sessionId, int tabId){
        List<Tab> tabs = tr.findByTabId(tabId);
        int projectId = tabs.get(0).getProjectId();

        List<DataProjectTeam> dataProjectTeams = dptr.findByProjectIdQry(projectId);
        String dataProjectTeam = modelToString(dataProjectTeams);

        List<Integer> userPrivacy = new ArrayList<Integer>();
        List<UserPrivacyTab> userPrivacyTabList = upr.findByTabId(tabId);
        String data = "";
        for(int i = 0; i< userPrivacyTabList.size(); i++){
            userPrivacy.add(userPrivacyTabList.get(i).getUserId());
            data += userPrivacyTabList.get(i).getUserId()+",";
        }

        data = (!data.equalsIgnoreCase(""))
                ? data.substring(0, data.lastIndexOf(",")) : "";

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("dataTeam", dataProjectTeam);
        jsonObject.addProperty("selectedUser", data);

        return jsonObject.toString();
    }

    public String modelToString(List<DataProjectTeam> dataProjectTeama){
        ObjectMapper mapper = new ObjectMapper();
        String dataProjectTeam = null;
        try{
            dataProjectTeam = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dataProjectTeama);
        }catch (Exception e){
            e.printStackTrace();
        }
        return dataProjectTeam;
    }
}
