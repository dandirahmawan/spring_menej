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

//	@Autowired
//	ViewLabelModuleRepo vlmr;

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
	public List<DataModule> getData(@RequestParam int projectId, int userId) {
		List<DataModule> data = ms.getDataByUser(projectId, userId);
		return data; 
	}

//	@PostMapping("/insert")
//	public List<Object> insertData(@RequestParam String moduleName, String userId, String dueDate,
//                                   String description, int userLogin, int projectId, String status, int section) {
//		List<Object> dm = null;
//		try {
//			dm = ms.insertModule(userId, moduleName, dueDate, description, userLogin, projectId, status, section);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		return dm;
//	}

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

//	@PostMapping("/update")
//	public Object updateModule(String date,
//							   String moduleId,
//							   String status,
//							   String moduleName,
//							   String assigned,
//							   String desc,
//							   String labelModule,
//							   String checklist,
//							   Integer section){
//		SimpleDateFormat sdm = new SimpleDateFormat("yyyy-MM-dd");
//		Date dateDate = null;
//		try{
//			dateDate = sdm.parse(date);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//
//		int moduleIdInt = Integer.parseInt(moduleId);
//		ms.updateModule(dateDate, moduleIdInt, status, assigned, desc, moduleName, section);
//
//		/*set data label module*/
//		ls.setDataLabelModule(moduleIdInt, labelModule);
//
//		/*set data bugs*/
//        try{
//            bs.updateDataBugs(checklist);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        /*get data module*/
//		ViewModule dataModule = vmr.findByModulId(Integer.parseInt(moduleId));
//
//		Map map = new HashMap<String, Object>();
//		map.put("success", true);
//		map.put("data", dataModule);
//		return map;
//	}

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
