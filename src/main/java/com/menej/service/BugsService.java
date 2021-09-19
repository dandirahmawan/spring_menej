package com.menej.service;

import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.menej.model.db.Bugs;
import com.menej.model.view.ViewBugs;
import com.menej.repo.BugsRepo;
import com.menej.repo.ViewBugsRepo;

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
	
//	public int closeBugs(int bugsId){
//		Bugs dataBugs = br.findByBugsId(bugsId);
//		dataBugs.setBugStatus("C");
//		br.save(dataBugs);
//		return 1;
//	}
//
//	public int uncloseBugs(int bugsId){
//		Bugs dataBugs = br.findByBugsId(bugsId);
//		dataBugs.setBugStatus("P");
//		br.save(dataBugs);
//		return 1;
//	}

	public void updateDataBugs(List<ViewBugs> checklist){
		for(ViewBugs item : checklist){
			int bugsId 	= item.getBugsId();
			String note 	= item.getNote();
			String status 	= item.getBugStatus();
			String isDelete	= item.getIsDelete();
			Date crateDate 	= item.getCreateDate();
			int moduleId	= item.getModulId();
			int projectId 	= item.getProjectId();
			int userId 		= item.getUserId();

			try{
				this.updateInsertTableBugs(bugsId, status, note, userId, projectId, moduleId, isDelete, crateDate);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}

	public void updateInsertTableBugs(int bugsId,
								String status,
								String notes,
								int userId,
								int projectId,
								int moduleId,
								String isDelete,
								Date createDate) throws Exception{

		Date now = new Date();
		Bugs bugs = new Bugs();
		if(bugsId > 0) {
			bugs.setBugsId(bugsId);
			bugs.setCreateDate(createDate);
		}else{
//			System.out.println(createDate);
			bugs.setCreateDate(now);
		}
		bugs.setNote(notes);
		bugs.setBugStatus(status);
		bugs.setModulId(moduleId);
		bugs.setProjectId(projectId);
		bugs.setIsDelete(isDelete);
		bugs.setCreatedBy(userId);
		br.save(bugs);
	}
}
