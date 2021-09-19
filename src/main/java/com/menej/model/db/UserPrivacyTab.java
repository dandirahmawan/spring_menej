package com.menej.model.db;

import com.menej.keys.UserPrivacyKey;

import javax.persistence.*;

@Entity
@Table(name = "user_privacy_tab")
@IdClass(UserPrivacyKey.class)
public class UserPrivacyTab {
    @Id
    @Column(name = "user_id")
    int userId;

    @Id
    @Column(name = "tab_id")
    int tabId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTabId() {
        return tabId;
    }

    public void setTabId(int tabId) {
        this.tabId = tabId;
    }
}
