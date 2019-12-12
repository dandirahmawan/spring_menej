package com.example.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Bugs;
import com.example.model.DataModule;
import com.example.model.DataModulePage;
import com.example.model.DataProject;
import com.example.model.DataProjectTeam;
import com.example.model.DetailModule;
import com.example.model.DocumentFile;
import com.example.service.BugsService;
import com.example.service.DocumentFileService;
import com.example.service.ModuleService;
import com.example.service.ProjectService;
import com.example.service.ProjectTeamService;


@RestController
public class ModuleController {
	@Autowired
	ModuleService ms;

	@Autowired
	ProjectService ps;

	@Autowired
	ProjectTeamService pts;

	@Autowired
	BugsService bs;

	@Autowired
	DocumentFileService dfs;

	@PostMapping("/project")
	public List<DataModule> getData(@RequestParam int projectId, int userId) {
		List<DataModule> data = ms.getDataByUser(projectId, userId);
		return data;
	}

	@PostMapping("/insert_module")
	public List<DataModule> insertData(@RequestParam String moduleName, int userId, String dueDate, String description, int userLogin, int projectId) {
		List<DataModule> dm = null;
		try {
			dm = ms.insertModule(userId, moduleName, dueDate, description, userLogin, projectId);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dm;
	}

	@PostMapping("/data_module")
	public List<DataModulePage> getDataModule(@RequestParam int projectId, int userId){
		List<DataModule> dm = ms.getDataByUser(projectId, userId);
		List<DataProject> dp = ps.getDataListProjectById(projectId);
		List<DataProjectTeam> dpt = pts.getDataProjectTeam(projectId);

		List<DataModulePage> dmpList = new ArrayList<DataModulePage>();
		DataModulePage dmp = new DataModulePage();
		dmp.setDataModule(dm);
		dmp.setDataProject(dp);
		dmp.setDataProjectTeam(dpt);
		dmpList.add(dmp);
		return dmpList;
	} 

	@PostMapping("/delete_module")
	public String deleteModule(@RequestParam String proposalId) {
		String[] dataDelete = proposalId.split(",");
		ms.deleteModule(dataDelete);
		return null;
	}

	@PostMapping("/update_module")
	public String updateModule(String date, String moduleId, String status, int pic, String desc){
		SimpleDateFormat sdm = new SimpleDateFormat("yyyy-MM-dd");
		Date dateDate = null;
		try{
			dateDate = sdm.parse(date);
		}catch(Exception e){
			e.printStackTrace();
		}

		System.out.println(dateDate+" "+moduleId);

		int moduleIdInt = Integer.parseInt(moduleId);
		ms.updateModule(dateDate, moduleIdInt, status, pic);
		return "success";
	}

	@PostMapping("/detail_module")
	public DetailModule getDataDetail(@RequestParam int moduleId){
		List<Bugs> bugs = bs.getBugsByModuleId(moduleId);
		List<DocumentFile> documentFile = dfs.getDataListDocFile(moduleId);
		DetailModule detailModule = new DetailModule(bugs, documentFile);
		return detailModule;
	}
	
}
