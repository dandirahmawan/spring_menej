package com.example.controllers;

import com.example.Utils;
import com.example.model.UserPrivacy;
import com.example.repo.UserPrivacyRepo;
import com.example.service.UserPrivacyService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserPrivacyController {
    @Autowired
    UserPrivacyService ups;

    @Autowired
    UserPrivacyRepo upr;

//    @PostMapping("/set_user_privacy")
//    public String setUserPrivacy(int userId, String sessionId, String userSelected, int tabId){
//        Utils utils = new Utils();
//        Boolean isAccess = utils.getAccess(userId, sessionId);
//        if(isAccess){
//            ups.deleteUserPrivacyByTabId(tabId);
//            String[] userSelecteds = userSelected.split(",");
//            for(int i = 0;i<userSelecteds.length;i++){
//                int userIdInt = Integer.parseInt(userSelecteds[i]);
//                ups.insertUserPrivacy(userIdInt, tabId);
//            }
//        }
//        return null;
//    }

    @PostMapping("/get_user_privacy")
    public String getUserPrivacy(int userId, String sessionId, int tabId){
        List<Integer> userPrivacy = new ArrayList<Integer>();
        List<UserPrivacy> userPrivacyList = upr.findByTabId(tabId);
        String data = "";
        for(int i = 0;i<userPrivacyList.size();i++){
            userPrivacy.add(userPrivacyList.get(i).getUserId());
            data += userPrivacyList.get(i).getUserId()+",";
        }
        data = data.substring(0, data.lastIndexOf(","));
        return data;
    }
}
