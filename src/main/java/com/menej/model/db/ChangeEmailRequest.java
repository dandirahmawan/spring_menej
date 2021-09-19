package com.menej.model.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "change_email_request")
public class ChangeEmailRequest {
    @Id
    @Column(name = "user_id")
    int userId;

    @Column(name = "email_request")
    String emailRequest;

    @Column(name = "code_request")
    String codeRequest;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmailRequest() {
        return emailRequest;
    }

    public void setEmailRequest(String emailRequest) {
        this.emailRequest = emailRequest;
    }

    public String getCodeRequest() {
        return codeRequest;
    }

    public void setCodeRequest(String codeRequest) {
        this.codeRequest = codeRequest;
    }
}
