package com.menej.service;

import com.menej.model.db.UserPrivacyTab;
import com.menej.repo.UserPrivacyTabRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPrivacyService {
    @Autowired
    UserPrivacyTabRepo userPrivacyRepo;

    public String insertUserPrivacy(int userId, int tabId){
        UserPrivacyTab userPrivacyTab = new UserPrivacyTab();
        userPrivacyTab.setUserId(userId);
        userPrivacyTab.setTabId(tabId);
        userPrivacyRepo.save(userPrivacyTab);

        return null;
    }

    public String deleteUserPrivacyByTabId(int tabId){
        List<UserPrivacyTab> userPrivacyTabList = userPrivacyRepo.findByTabId(tabId);
        for(int i = 0; i< userPrivacyTabList.size(); i++){
            userPrivacyRepo.delete(userPrivacyTabList.get(i));
        }
        return null;
    }
}
