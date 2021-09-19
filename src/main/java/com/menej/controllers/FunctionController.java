package com.menej.controllers;

import com.menej.model.FunctionData;
import com.menej.repo.FunctionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FunctionController {
    @Autowired
    FunctionRepo fr;

    @PostMapping("/function_data")
    public List<FunctionData> getFunctionData(int projectId, String type){
        System.out.println(projectId);
        List<FunctionData> functionDataList = new ArrayList<FunctionData>();

        if(type.equals("MOD") || type.equals("mod")){
            functionDataList = fr.findAllModQry(projectId);
        }else{
            functionDataList = fr.findAllDocQry(projectId);
        }
        return functionDataList;
    }
}