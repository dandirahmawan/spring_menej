package com.menej.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.menej.DBConnection;
import com.menej.model.DetailModule;
import com.menej.model.PermitionProject;
import com.menej.model.db.AssignedModule;
import com.menej.model.db.DocumentFile;
import com.menej.model.request.ModuleEdit;
import com.menej.model.request.ModuleParamEdit;
import com.menej.model.view.*;
import com.menej.repo.*;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.menej.model.DataModule;
import com.menej.model.db.Modul;

@Service
@Slf4j
public class ModuleService {
	@Autowired
	ModuleRepo mr;

	@Autowired
	AssignedModuleRepo amr;

	@Autowired
	ViewAssignedModuleRepo vamr;

	@Autowired
    ViewModuleRepo vmr;

	@Autowired
	LabelService ls;

	@Autowired
	BugsService bs;

	@Autowired
	DocumentFileService dfs;

	@Autowired
	PermitionService permServ;

	@Autowired
	ViewStatusModuleRepo vsmr;

	@Autowired
	ViewLabelModuleRepo vlmr;

	@Autowired
	ViewDocumentFileRepo vdfr;

	public List<DataModule> getDataByUser(int pi, int id){
		return mr.fetchDataModuleByUser(pi, id, String.valueOf(id));
	}

	public List<DataModule> getDataProjectId(int projectId){
		return mr.fetchDataModuleByProjctId(projectId);
	}

	public List<DataModule> getDataByUserId(int userId){
		String userIdString = String.valueOf(userId);
		return mr.fetchDataModuleByUserId(userId, userIdString);
	}
	
	public List<DataModule> getDataByUserIdDetail(int userLogin){
		return mr.fetchDataUserDetail(String.valueOf(userLogin), userLogin);
	}
	
//	public List<Object>
//	insertModule(String userId, String moduleName, String dueDate, String desc,
//									 int userLogin, int projectId, String status, int section) throws ParseException{
//		Date date = new Date();
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		Date dueDate2 = format.parse(dueDate);
//
//		Modul modul = new Modul();
//		modul.setCreatedBy(userLogin);
//		modul.setCreatedDate(date);
//		modul.setDescription(desc);
//		modul.setModulName(moduleName);
//		modul.setProjectId(projectId);
//		modul.setEndDate(dueDate2);
//		modul.setModulStatus(status);
//		modul.setIsTrash("N");
//		modul.setUpdatedDate(date);
//		modul.setSectionId(section);
//		modul.setUpdatedBy(String.valueOf(userLogin));
//		mr.save(modul);
//
//		Optional<Modul> modulOptional = mr.findTopByProjectIdOrderByModulIdDesc(projectId);
//		int moduleId = modulOptional.map(Modul::getModulId).orElse(0);
//
//		String[] users = userId.split(",");
//		if(moduleId > 0) {
//			for(int i = 0;i<users.length;i++){
//				int userIdInt = Integer.parseInt(users[i]);
//				AssignedModule assignedModule = new AssignedModule();
//				assignedModule.setUserId(userIdInt);
//				assignedModule.setModuleId(moduleId);
//				amr.save(assignedModule);
//			}
//		}
//
////		Optional<DataModule> dm = mr.fetchLast(userLogin, date, moduleName, projectId);
//        Optional<ViewModule> dm = vmr.fetchLast(userLogin, date, moduleName, projectId);
//		/*get data user assigned*/
//		int newModuleId = dm.map(ViewModule::getModulId).orElse(0);
//		List<ViewAssignedModule> viewAssignedModules = vamr.findByModuleId(newModuleId);
//
//		Map<String, Object> data = new HashMap<>();
//		data.put("module", dm);
//		data.put("assignTo", viewAssignedModules);
//
//		List<Object> listData = new ArrayList<>();
//		listData.add(data);
//		return listData;
//	}
//
//	public DataModule getDataModuleByModuleId(int moduleId, int projectId) {
//		return mr.fetchDataModuleByModuleId(projectId, moduleId);
//	}
	
	public String deleteModule(String[] dataDelete){
		for(int i = 0;i<dataDelete.length;i++){
			int id = Integer.parseInt(dataDelete[i]);
			Modul modul = mr.findBymodulId(id);
			modul.setIsTrash("Y");
			mr.save(modul);
		}
		return null;
	}

	public Map<String, Object> updateModule(ModuleEdit moduleEditParam){
		ModuleEdit moduleEdit = moduleEditParam;
		String assigned = moduleEdit.getAssigned();
		String date = moduleEdit.getDate();
		String desc = moduleEdit.getDesc();
		String labelModule = moduleEdit.getLabelModule();
		Integer modulId = moduleEdit.getModulId();
		String moduleName = moduleEdit.getModuleName();
		Integer section = moduleEdit.getSection();
		Integer status = moduleEdit.getStatus();

//		System.out.println(modulId);
//		System.out.println(assigned);
//		System.out.println(date);
//		System.out.println(desc);
//		System.out.println(labelModule);
//		System.out.println(modulId);
//		System.out.println(moduleName);
//		System.out.println(section);
//		System.out.println(status);

		SimpleDateFormat sdm = new SimpleDateFormat("yyyy-MM-dd");
		Date dateDate = null;
		try{
			dateDate = sdm.parse(date);
		}catch(Exception e){
			e.printStackTrace();
		}

		int moduleIdInt = 0;
		try{
			moduleIdInt = modulId;
		}catch (Exception e){
//			e.printStackTrace();
		}

		this.updateTableModule(dateDate, moduleIdInt, status, assigned, desc, moduleName, section);
		/*set data label module*/
		ls.setDataLabelModule(moduleIdInt, labelModule);

		ViewModule dataModule = vmr.findByModulId(modulId);
		Map map = new HashMap<String, Object>();
		map.put("module", dataModule);
		return map;
	}

	public String updateTableModule(Date date, int id, Integer status, String assigned, String desc, String moduleName, Integer section){
		Modul modul = mr.findBymodulId(id);
		Date dateNow = new Date();
		modul.setEndDate(date);
		modul.setUpdatedBy("1");
		modul.setUpdatedDate(dateNow);
		modul.setModulStatus(String.valueOf(status));
		modul.setDescription(desc);
		modul.setModulName(moduleName);
		modul.setSectionId(section);
		mr.save(modul);

		this.setAssigneed(assigned, id);
		return null;
	}

	public String handoverModule(String[] userF, int userT, int projectId){
		DBConnection gc = new DBConnection();
		Connection conn = gc.getConnection();
		PreparedStatement pr = null;

		String uPar = "";
		String prPar = "";
		for(int i = 0;i<userF.length;i++){
            prPar += (i == userF.length - 1) ? "?" : "?,";
		}

		String sql = "UPDATE modul SET user_id = ? WHERE project_id = ? AND user_id IN ("+prPar+")";

		try{
			pr = conn.prepareStatement(sql);
			pr.setInt(1, userT);
			pr.setInt(2, projectId);
			for(int i = 0;i<userF.length;i++){
                int x = 3 + i;
			    pr.setString(x, userF[i]);
            }
			int exc = pr.executeUpdate();
		}catch (Exception e){
			e.printStackTrace();
		}

		return null;
	}

	public Object newModule(ModuleParamEdit moduleEditParam){
		ModuleEdit moduleEdit = moduleEditParam.getModule();
		Integer pi = Integer.parseInt(moduleEdit.getProjectId());
		String name = moduleEdit.getModuleName();
		String desc = moduleEdit.getDesc();
		String date = moduleEdit.getDate();
		Integer section = moduleEdit.getSection();
		Integer status = moduleEdit.getStatus();
		String labels = moduleEdit.getLabelModule();
		String assigness = moduleEdit.getAssigned();
		Integer cb = moduleEdit.getCreatedBy();

		SimpleDateFormat sdm = new SimpleDateFormat("yyyy-MM-dd");
		Date dateDate = null;
		try{
			dateDate = sdm.parse(date);
		}catch(Exception e){
			e.printStackTrace();
		}

		int mi = this.insertTableModule(dateDate, String.valueOf(status), desc, name, pi, cb, section);
		if(mi > 0) {
			this.setAssigneed(assigness, mi);
			ls.setDataLabelModule(mi, labels);

			/*insert data checklist*/
			List<ViewBugs> checklist = moduleEditParam.getChecklist();
			for(ViewBugs item : checklist){
				try{
					bs.updateInsertTableBugs(item.getBugsId(),
											item.getBugStatus(),
											item.getNote(),
											item.getUserId(),
											item.getProjectId(),
											mi,
											item.getIsDelete(),
											item.getCreateDate());
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}

		return mi;
	}

	public Integer insertTableModule(Date date,
									String status,
									String desc,
									String moduleName,
									int projectId,
									int createdBy,
									Integer section){
		Modul modul = new Modul();
		Date dateNow = new Date();

		modul.setEndDate(date);
		modul.setUpdatedDate(dateNow);
		modul.setModulStatus(status);
		modul.setDescription(desc);
		modul.setProjectId(projectId);
		modul.setModulName(moduleName);
		modul.setSectionId(section);
		modul.setCreatedBy(createdBy);
		modul.setCreatedDate(dateNow);
		mr.save(modul);

		Optional<Modul> mod = mr.findTopByProjectIdAndCreatedByOrderByModulIdDesc(projectId, createdBy);
		Integer mi = mod.map(Modul::getModulId).orElse(0);
		return mi;
	}

	public Object setAssigneed(String assigned, int id){
		try {
			JSONArray jsonArray = (JSONArray) new JSONParser().parse(assigned);
			if(jsonArray.size() > 0){
				log.info("upadating data assigned module.....");
				/*delete data assigned*/
				amr.deleteByModuleId(id);

				for(int i = 0;i<jsonArray.size();i++){
					JSONObject jo = (JSONObject) jsonArray.get(i);
					Long userId = (Long) jo.get("userId");

					AssignedModule assignedModule = new AssignedModule();
					assignedModule.setModuleId(id);
					assignedModule.setUserId(userId.intValue());
					amr.save(assignedModule);
				}
			}
		}catch (Exception e){
			e.printStackTrace();
			log.warn("parse json assigned is failed");
		}

		return null;
	}

	public DetailModule getDataDetail(Integer moduleId, Integer userId, Integer projectId){
		List<ViewBugs> bugs = new ArrayList<ViewBugs>();
//		List<DocumentFile> documentFile = new ArrayList<DocumentFile>();
		List<ViewDocumentFile> viewDocumentFiles = new ArrayList<>();
		List<PermitionProject> permitionProjects = new ArrayList<PermitionProject>();
		ViewModule dataModule = vmr.findByModulId(moduleId);
		bugs = bs.getViewBugsByModuleId(moduleId);
//		documentFile = dfs.getDataListDocFile(moduleId);
		viewDocumentFiles = vdfr.findByModuleId(moduleId);
		permitionProjects = permServ.getDataPermition(userId, projectId);
		List<ViewStatusModule> viewStatusModules = vsmr.findByProjectId(projectId);
		List<ViewAssignedModule> assignedModules = vamr.findByModuleId(moduleId);
		List<ViewLabelModule> viewLabelModules = vlmr.findByModuleId(moduleId);

		DetailModule detailModule =
				new DetailModule(bugs, viewDocumentFiles, permitionProjects, dataModule, viewStatusModules, assignedModules, viewLabelModules);

		return detailModule;
	}
}
