package com.example.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.DataModule;
import com.example.model.Modul;
import com.example.repo.ModuleRepo;

@Service
public class ModuleService {
	@Autowired
	ModuleRepo mr;
	
	public List<DataModule> getDataByUser(int pi, int id){
		return mr.fetchDataModuleByUser(pi);
	}

	public List<DataModule> getDataByUserId(int userId){
		return mr.fetchDataModuleByUserId(userId);
	}

	public List<DataModule> insertModule(int userId, String moduleName, String dueDate, String desc, int userLogin, int projectId) throws ParseException{
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dueDate2 = format.parse(dueDate);

		Modul modul = new Modul();

		modul.setCreatedBy(userLogin);
		modul.setCreatedDate(date);
		modul.setDescription(desc);
		modul.setUserId(userId);
		modul.setModulName(moduleName);
		modul.setProjectId(projectId);
		modul.setEndDate(dueDate2);
		modul.setModulStatus("P");
		modul.setIsTrash("N");
		modul.setUpdatedDate(date);
		modul.setUpdatedBy(String.valueOf(userLogin));
		mr.save(modul);

		List<DataModule> dm = mr.fetchLast(userLogin, date, moduleName, projectId);
		return dm;
	}

	public String deleteModule(String[] dataDelete){
		for(int i = 0;i<dataDelete.length;i++){
			int id = Integer.parseInt(dataDelete[i]);
			Modul modul = mr.findBymodulId(id);
			modul.setIsTrash("Y");
			mr.save(modul);
		}
		return null;
	}

	public String updateModule(Date date, int id, String status, int pic){
		Modul modul = mr.findBymodulId(id);
		Date dateNow = new Date();
		modul.setEndDate(date);
		modul.setUpdatedBy("1");
		modul.setUpdatedDate(dateNow);
		modul.setModulStatus(status);
		modul.setUserId(pic);
		mr.save(modul);
		return null;
	}
}
