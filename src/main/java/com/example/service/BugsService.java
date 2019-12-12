package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Bugs;
import com.example.model.BugsUserDetail;
import com.example.repo.BugsRepo;

@Service
public class BugsService {
	@Autowired
	BugsRepo br;
	
	public List<Bugs> getBugs(){
		return br.findAll();
	}

	public List<Bugs> getBugsByModuleId(int id){
		return br.findBymodulId(id);
	}

	public Bugs insertBugs(int moduleId, int projectId, String bugs){
		Bugs bugsModel = new Bugs();
		bugsModel.setModulId(moduleId);
		bugsModel.setProjectId(projectId);
		bugsModel.setNote(bugs);
		br.save(bugsModel);
		return bugsModel;
	}

	public List<BugsUserDetail> getDataBugsUser(int userId){
		return br.findByUserId(userId);
	}

	public int deleteBugs(int moduleId, String note){
		Bugs dataDelete = br.findByModulIdAndNote(moduleId, note);
		br.delete(dataDelete);
		return 1;
	}
}
