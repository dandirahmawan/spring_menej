package com.example.repo;

import java.util.List;

import com.example.model.ViewUserRelation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ViewUserRelationRepo extends JpaRepository<ViewUserRelation, String>{
    @Query("SELECT new com.example.model.ViewUserRelation( \n" 
                    +"userId, \n"
                    +"userOne, \n"
                    +"userTwo, \n"
                    +"userName, \n"
                    +"emailUser, \n"
                    +"picProfile) \n" 
                    +"FROM ViewUserRelation \n"
                    +"WHERE userId != ?1 AND (userOne = ?2 OR userTwo = ?3)")
    List<ViewUserRelation> findUserRelation(int userId, int userOne, int userTwo);
}