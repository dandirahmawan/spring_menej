package com.menej.repo;

import java.util.List;

import com.menej.model.view.ViewUserRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ViewUserRelationRepo extends JpaRepository<ViewUserRelation, String>{
//    @Query("SELECT new ViewUserRelation( \n"
//                    +"userId, \n"
//                    +"userOne, \n"
//                    +"userTwo, \n"
//                    +"userName, \n"
//                    +"emailUser, \n"
//                    +"picProfile) \n" 
//                    +"FROM ViewUserRelation \n"
//                    +"WHERE userId != ?1 AND (userOne = ?2 OR userTwo = ?3)")
	@Query("SELECT data \n" 
            +"FROM ViewUserRelation data \n"
            +"WHERE userId <> ?1 AND (userOne = ?2 OR userTwo = ?3)")
    List<ViewUserRelation> findUserRelation(int userId, int userOne, int userTwo);
}