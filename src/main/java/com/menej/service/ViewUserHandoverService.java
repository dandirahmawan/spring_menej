package com.menej.service;

import com.menej.model.view.ViewUserHandover;
import com.menej.repo.ViewUserHandoverRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewUserHandoverService {
    @Autowired
    ViewUserHandoverRepo viewUserHandoverRepo;

    public List<ViewUserHandover> getListUserHandover(int projectId){
        return viewUserHandoverRepo.findByProjectIdQry(projectId);
    }
}
