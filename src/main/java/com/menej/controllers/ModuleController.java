package com.menej.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.menej.model.*;
import com.menej.model.db.*;
import com.menej.model.request.ModuleEdit;
import com.menej.model.request.ModuleParamEdit;
import com.menej.model.view.*;
import com.menej.repo.*;
import com.menej.repo.SectionModuleRepo;
import com.menej.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.menej.Utils;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/module")
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
	PermitionService permServ;

	@Autowired
	ViewNoteRepo vnr;

	@Autowired
	ViewUserHandoverService viewUserHandoverService;

	@Autowired
	TabRepo tabRepo;

	@Autowired
	ViewStatusModuleRepo vsmr;

	@Autowired
	LabelsRepo lr;

	@Autowired
	ModuleRepo mr;

	@Autowired
	LabelService ls;

	@Autowired
	ViewProjectTeamRepo vptr;

	@Autowired
	ViewAssignedModuleRepo vamr;

	@Autowired
	SectionRepo sr;

	@Autowired
	SectionModuleRepo smr;

	@Autowired
	ViewModuleRepo vmr;

	@PostMapping()
	public List<DataModule> getData(@RequestParam int projectId, HttpServletRequest request) {
		int userId = Integer.parseInt(request.getHeader("userid"));
		List<DataModule> data = ms.getDataByUser(projectId, userId);
		return data; 
	}

	@PostMapping("/insert")
	public Object insertData(@RequestBody ModuleParamEdit module) {
		return this.updateModule(module);
	}

	@PostMapping("/data")
	public List<DataModulePage> getDataModule(@RequestParam int projectId, int userId, String sessionId){
		List<DataModulePage> dmpList = new ArrayList<DataModulePage>();

		/*fetch data module*/
		List<Section> dm = sr.findByProjectId(projectId);
		List<SectionModule> sm = smr.findByProjectId(projectId);
		List<DataProject> dp = ps.getDataListProjectById(projectId, userId);
		List<DataProjectTeam> dpt = pts.getDataProjectTeam(projectId);
		List<PermitionProject> pp = permServ.getDataPermition(userId, projectId);
		List<ViewNote> vn = vnr.findByProjectId(projectId);
		List<DataTab> tabs = tabRepo.findByProjectIdQuery(projectId, userId);
		List<ViewStatusModule> viewStatusModules = vsmr.findByProjectId(projectId);
		List<Labels> labelsList = lr.findByProjectId(projectId);
		List<ViewAssignedModule> assignedModules = vamr.findByProjectId(projectId);

		DataModulePage dmp = new DataModulePage();
		dmp.setDataModule(dm);
		dmp.setDataProject(dp);
		dmp.setDataProjectTeam(dpt);
		dmp.setPermitionProjects(pp);
		dmp.setNote(vn);
		dmp.setTabs(tabs);
		dmp.setStatusModules(viewStatusModules);
		dmp.setLabelsList(labelsList);
		dmp.setAssignedModules(assignedModules);
		dmp.setSectionModules(sm);
		dmpList.add(dmp);
		return dmpList;
	}
	
	@PostMapping("/data_ol")
	public List<DataModule> getListModule(String userId, String sessionId, String user_prm){
		Utils utils = new Utils();
		int userIdInt = Integer.parseInt(userId);
		Boolean isAccess = utils.getAccess(userIdInt, sessionId);
		if(isAccess) {
			
		}
		return null;
	}

	@PostMapping("/delete")
	public String deleteModule(@RequestParam String moduleId) {
		String[] dataDelete = moduleId.split(",");
		ms.deleteModule(dataDelete);
		return null;
	}

	@PostMapping("/new")
	public Object newModule(@RequestBody ModuleParamEdit module){
		ModuleEdit me = module.getModule();
		Integer pi = Integer.parseInt(me.getProjectId());
		Integer mi = (Integer) ms.newModule(module);
		Integer ui = me.getCreatedBy();
		return ms.getDataDetail(mi, ui, pi);
	}

	@PostMapping("/update")
	public Object updateModule(@RequestBody ModuleParamEdit module){
		ModuleEdit moduleEdit = module.getModule();
		List<ViewBugs> dataBugs = module.getChecklist();

		/*keep this sequence process*/
        bs.updateDataBugs(dataBugs);
		ms.updateModule(moduleEdit);

		List<ViewBugs> bugs = bs.getViewBugsByModuleId(module.getModule().getModulId());
		Map<String, Object> mod = ms.updateModule(moduleEdit);

		Map<String, Object> data = new HashMap<>();
		data.put("success", true);
		data.put("module", mod.get("module"));
		data.put("checklist", bugs);
		return data;
	}

	@PostMapping("/detail")
	public DetailModule getDataDetail(@RequestParam int moduleId, int userId, int projectId){
		DetailModule dm = ms.getDataDetail(moduleId, userId, projectId);
		return dm;
	}

	@PostMapping("/assigning")
	public List<ViewProjectTeam> moduleAssigning(String projectId){
		List<ViewProjectTeam> viewProjectTeams = vptr.findByProjectId(Integer.parseInt(projectId));
		return viewProjectTeams;
	}

	@PostMapping("/user")
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
