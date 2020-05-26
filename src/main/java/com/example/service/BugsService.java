package com.example.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Bugs;
import com.example.model.BugsUserDetail;
import com.example.model.view.ViewBugs;
import com.example.repo.BugsRepo;
import com.example.repo.ViewBugsRepo;

@Service
public class BugsService {
	@Autowired
	BugsRepo br;
	
	@Autowired
	ViewBugsRepo vbr;
	
	public List<Bugs> getBugs(){
		return br.findAll();
	}

	public List<Bugs> getBugsByModuleId(int id){
		return br.findBymodulId(id);
	}
	
	public Bugs getBugsByBugsId(int bugsId){
		return br.findByBugsId(bugsId);
	}
	
	public List<ViewBugs> getViewBugsByUserId(int userId){
		return vbr.findQueryByUserId(userId, userId);
	}
	
	public List<ViewBugs> getViewBugsByUserIdDetail(int userId, int userLogin){
		return vbr.findQueryByUserIdDetail(userLogin, userId);
	}
	
	public List<ViewBugs> getViewBugsByModuleId(int moduleId){
		return vbr.findByModulId(moduleId);
	}
	
	public List<ViewBugs> getViewBugsByProjectId(int projectId, int userId){
		return vbr.findByProjectId(projectId, userId, userId);
	}

	public String editBugs(int bugsId, String bugs){
	    Bugs bugs1 = br.findByBugsId(bugsId);
	    bugs1.setNote(bugs);
	    br.save(bugs1);
	    return null;
    }

	public ViewBugs insertBugs(int moduleId, int projectId, String bugs, int userId){
		Date date = new Date();
		Bugs bugsModel = new Bugs();
		ViewBugs viewBugs = null;
		if(moduleId > 0) {
			bugsModel.setModulId(moduleId);
			bugsModel.setProjectId(projectId);
			bugsModel.setNote(bugs);
			bugsModel.setIsDelete("N");
			bugsModel.setCreateDate(date);
			bugsModel.setBugStatus("P");
			bugsModel.setCreatedBy(userId);
			br.save(bugsModel);
			viewBugs = vbr.findByModulIdAndNoteAndCreateDate(moduleId, bugs, date);
			br.save(bugsModel);
		}
		return viewBugs;
	}

	public int deleteBugs(int bugsId){
		Bugs dataDelete = br.findByBugsId(bugsId);
		System.out.println(dataDelete.getNote());
		br.delete(dataDelete);
		return 1;
	}
	
	public int closeBugs(int bugsId){
		Bugs dataBugs = br.findByBugsId(bugsId);
		dataBugs.setBugStatus("C");
		br.save(dataBugs);
		return 1;
	}
	
	public int uncloseBugs(int bugsId){
		Bugs dataBugs = br.findByBugsId(bugsId);
		dataBugs.setBugStatus("P");
		br.save(dataBugs);
		return 1;
	}
}
