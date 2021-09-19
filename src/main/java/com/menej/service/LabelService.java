package com.menej.service;

import com.menej.model.db.LabelModule;
import com.menej.repo.LabelModuleRepo;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class LabelService {

    @Autowired
    LabelModuleRepo lmr;

    public void setDataLabelModule(int moduleId, String labelModule){
        try{
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(labelModule);

            /*delete data label module*/
            lmr.deleteByModuleId(moduleId);

            /*insert new data label module*/
            for(int i = 0;i<jsonArray.size();i++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String label = jsonObject.get("label").toString();
                Long projectId = (Long) (jsonObject.get("projectId"));

                LabelModule labelModule1 = new LabelModule();
                labelModule1.setLabel(label);
                labelModule1.setProjectId(projectId.intValue());
                labelModule1.setModuleId(moduleId);

                lmr.save(labelModule1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
