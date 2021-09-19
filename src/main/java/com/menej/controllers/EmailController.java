package com.menej.controllers;

import com.google.gson.JsonObject;
import com.menej.model.db.ChangeEmailRequest;
import com.menej.repo.ChangeEmailRequestRepo;
import com.menej.service.UserService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmailController {
    @Autowired
    ChangeEmailRequestRepo cerr;

    @Autowired
    UserService us;

    @PostMapping("/email_confirmation")
    public JSONObject confirmationEmail(int userId, String conf){
        JSONObject jsonObject = new JSONObject();
        List<ChangeEmailRequest> changeEmailRequestList = cerr.findByCodeRequestAndUserId(conf, userId);

        if(changeEmailRequestList.size() > 0){
            ChangeEmailRequest changeEmailRequest = changeEmailRequestList.get(0);
            String email = changeEmailRequest.getEmailRequest();
            us.confirmChangeEmail(userId, email);

            jsonObject.put("code", 1);
            jsonObject.put("message", email);
        }else{
            jsonObject.put("code", 0);
            jsonObject.put("message", "request data not found");
        }

//        jsonObject.put("data", changeEmailRequestList);
        return jsonObject;
    }
}
