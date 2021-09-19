package com.menej.controllers;

import com.menej.model.db.LabelModule;
import com.menej.model.db.Labels;
import com.menej.model.view.ViewLabelModule;
import com.menej.repo.LabelModuleRepo;
import com.menej.repo.LabelsRepo;
import com.menej.repo.ViewLabelModuleRepo;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.Date;
import java.util.List;

@RestController
public class LabelController {

    @Autowired
    LabelsRepo lr;

    @Autowired
    LabelModuleRepo lmr;

    @Autowired
    ViewLabelModuleRepo vlmr;

    @PostMapping("/label")
    public void addLabel(String label, int projectId, String color){
        Labels labels = new Labels();
        labels.setLabel(label);
        labels.setProjectId(projectId);
        labels.setColor(color);
        labels.setDate(new Date());
        lr.save(labels);
//        System.out.println("menmbahkan data label baru");
    }

    @PostMapping("/delete_label")
    public JSONObject deleteLabel(String label, int projectId){
        JSONObject jsonObject = new JSONObject();

        lr.deleteByProjectIdAndLabel(projectId, label);
        lmr.deleteByProjectIdAndLabel(projectId, label);

        List<Labels> labels = lr.findByProjectId(projectId);
        List<ViewLabelModule> labelModuleList = vlmr.findByProjectId(projectId);

        jsonObject.put("label", labels);
        jsonObject.put("labelModule", labelModuleList);

        return jsonObject;
    }

    @GetMapping("/label")
    public void getLabel(){
        System.out.println("menmbahkan data label baru");
    }
}
