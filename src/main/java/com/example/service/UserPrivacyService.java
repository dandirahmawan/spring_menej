package com.example.service;

import com.example.model.UserPrivacy;
import com.example.repo.UserPrivacyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPrivacyService {
    @Autowired
    UserPrivacyRepo userPrivacyRepo;

    public String insertUserPrivacy(int userId, int tabId){
        UserPrivacy userPrivacy = new UserPrivacy();
        userPrivacy.setUserId(userId);
        userPrivacy.setTabId(tabId);
        userPrivacyRepo.save(userPrivacy);

        return null;
    }

    public String deleteUserPrivacyByTabId(int tabId){
        List<UserPrivacy> userPrivacyList = userPrivacyRepo.findByTabId(tabId);
        for(int i = 0;i<userPrivacyList.size();i++){
            userPrivacyRepo.delete(userPrivacyList.get(i));
        }
        return null;
    }
}
