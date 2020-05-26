package com.example.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.model.*;
import com.example.model.view.ViewUserHandover;
import com.example.repo.TabRepo;
import com.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Utils;
import com.example.model.view.ViewBugs;
import com.example.model.view.ViewNote;
import com.example.repo.ViewNoteRepo;


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
	
	@Autowired
	PermitionService permServ;
	
	@Autowired
	ViewNoteRepo vnr;

	@Autowired
	ViewUserHandoverService viewUserHandoverService;

	@Autowired
	TabRepo tabRepo;

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
	public List<DataModulePage> getDataModule(@RequestParam int projectId, int userId, String sessionId){
		
		Utils utils = new Utils();
		Boolean isAccess = utils.getAccess(userId, sessionId);
		List<DataModulePage> dmpList = new ArrayList<DataModulePage>();
		
		if(isAccess) {
			List<DataModule> dm = ms.getDataByUser(projectId, userId);
			List<DataProject> dp = ps.getDataListProjectById(projectId, userId);
			List<DataProjectTeam> dpt = pts.getDataProjectTeam(projectId);
			List<PermitionProject> pp = permServ.getDataPermition(userId, projectId);
			List<ViewNote> vn = vnr.findByProjectId(projectId);
			List<DataTab> tabs = tabRepo.findByProjectIdQuery(projectId, userId);
			
			DataModulePage dmp = new DataModulePage();
			dmp.setDataModule(dm);
			dmp.setDataProject(dp);
			dmp.setDataProjectTeam(dpt);
			dmp.setPermitionProjects(pp);
			dmp.setNote(vn);;
			dmp.setTabs(tabs);
			dmpList.add(dmp);
		}
		
		return dmpList;
	}
	
	@PostMapping("/data_module_ol")
	public List<DataModule> getListModule(String userId, String sessionId, String user_prm){
		Utils utils = new Utils();
		int userIdInt = Integer.parseInt(userId);
		int userPrmInt = Integer.parseInt(user_prm);
		Boolean isAccess = utils.getAccess(userIdInt, sessionId);
		if(isAccess) {
			
		}
		return null;
	}

	@PostMapping("/delete_module")
	public String deleteModule(@RequestParam String moduleId) {
		String[] dataDelete = moduleId.split(",");
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
	public DetailModule getDataDetail(@RequestParam int moduleId, int userId, String sessionId, int projectId){
		Utils utils = new Utils();
		Boolean isAccess = utils.getAccess(userId, sessionId);
		
		List<ViewBugs> bugs = new ArrayList<ViewBugs>();
		List<DocumentFile> documentFile = new ArrayList<DocumentFile>();
		List<PermitionProject> permitionProjects = new ArrayList<PermitionProject>();
		DataModule dataModule =  ms.getDataModuleByModuleId(moduleId, projectId);
		DetailModule detailModule = null;
		
		if(isAccess) {
			bugs = bs.getViewBugsByModuleId(moduleId);
			documentFile = dfs.getDataListDocFile(moduleId);
			permitionProjects = permServ.getDataPermition(userId, projectId);
			detailModule = new DetailModule(bugs, documentFile, permitionProjects, dataModule);
		}
		return detailModule;
	}
	
	@PostMapping("/module_user")
	public List<DataModule> getDataModule(int userId, String sessionId){
		Utils utils = new Utils();
		Boolean isAccess = utils.getAccess(userId, sessionId);
		
		List<DataModule> dataModule = new ArrayList<DataModule>();
		if(isAccess) {
			dataModule = ms.getDataByUserId(userId);
		}
		return dataModule;
	}

	@PostMapping("/user_handover")
	public List<ViewUserHandover> getUserHandover(int projectId, int userId, String sessionId){
		Utils utils = new Utils();
		Boolean isAccess = utils.getAccess(userId, sessionId);

		List<ViewUserHandover> viewUserHandovers = new ArrayList<ViewUserHandover>();
		if(isAccess){
			viewUserHandovers = viewUserHandoverService.getListUserHandover(projectId);
		}
		return viewUserHandovers;
	}

	@PostMapping("/handover_module")
	public String getUserHandover(String user_f, int user_t, String sessionId, int userId, int projectId){
		Utils utils = new Utils();
		Boolean isAccess = utils.getAccess(userId, sessionId);
		String[] userF = user_f.split(",");
		ms.handoverModule(userF, user_t, projectId);
		return null;
	}
}
