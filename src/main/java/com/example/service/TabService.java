package com.example.service;

import com.example.model.Tab;
import com.example.repo.TabRepo;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Service
public class TabService {
    @Autowired
    TabRepo tr;

    public String newTab(int projectId, String tabName, int userId, String prv){
        List<Tab> tabs = tr.findByProjectIdAndTabNameAndUserIdAndIsDeleteNotQuery(projectId, tabName, userId);
        String rtn = null;
        if(tabs.size() > 0){
            rtn = "exists";
        }else{
            Date date = new Date();
            Tab tab = new Tab();
            tab.setTabName(tabName);
            tab.setProjectId(projectId);
            tab.setCreatedBy(userId);
            tab.setCreatedDate(date);
            tab.setPrivacy(prv);
            tr.save(tab);
        }
        return rtn;
    }

    public String createTable(int tabId, String file){
        System.out.println("create file frm "+tabId);
        List<Tab> tabs = tr.findByTabId(tabId);
        for(int i = 0;i<tabs.size();i++){
            Tab tab = tabs.get(i);
            tab.setFileFrm(file);
            tr.save(tab);
        }
        return null;
    }

    public List<Tab> tabData(int tabId){
        List<Tab> tabs = tr.findByTabId(tabId);
        return tabs;
    }

    public String newTabData(int tabId, String data){
        List<Tab> tabs = tr.findByTabId(tabId);
        for(int i = 0;i<tabs.size();i++) {
            int projectId = tabs.get(i).getProjectId();
            int userId = tabs.get(i).getCreatedBy();
            File folderTabFile = new File("../tab_file");
            if (!folderTabFile.exists()) folderTabFile.mkdir();

            File folderUser = new File(folderTabFile + "/usr_" + userId);
            if (!folderUser.exists()) folderUser.mkdir();

            File folderProject = new File(folderUser + "/prj_" + projectId);
            if (!folderProject.exists()) folderProject.mkdir();

            Path paths = Paths.get(folderProject+"/"+"tab_data_"+tabId+".txt");
            File file = new File(folderProject+"/"+"tab_data_"+tabId+".txt");
            try {
                if (!file.exists()) file.createNewFile();
                String str = data+"\r\n";
                byte[] strToBytes = str.getBytes();
                Files.write(paths, strToBytes, StandardOpenOption.APPEND);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public String copyTab(int tabId, String tabName, int userIdLogin, Boolean isRreplace){
        List<Tab> tabs = tr.findByTabId(tabId);
        for(int i = 0;i<tabs.size();i++) {
            int projectId = tabs.get(i).getProjectId();
            int userId = tabs.get(i).getCreatedBy();

            //insert copy tab to table tab
            Tab tab = new Tab();
            tab.setTabName(tabName);
            tab.setIsDelete("N");
            tab.setPrivacy("pr");
            tab.setCreatedDate(new Date());
            tab.setCreatedBy(userIdLogin);
            tab.setProjectId(projectId);
            if(isRreplace) {
                tab.setType("replace");
            }else{
                tab.setType("copy");
            }
            tab.setSource(tabId);
            tr.save(tab);

            //delete tab if isReplace is true
            if(isRreplace){
                List<Tab> tabsReplace = tr.findByProjectIdAndTabNameAndUserIdAndIsDeleteNotQuery(projectId, tabName, userIdLogin);
                for(int x = 0;x<tabsReplace.size();x++){
                    tabsReplace.get(i).setIsDelete("Y");
                    tr.save(tabsReplace.get(i));

                    //set replaced tab id colum
                    tab.setReplacedTabId(tabsReplace.get(i).getTabId());
                    tr.save(tab);
                }
            }

//            Path paths = Paths.get("../tab_file/usr_"+userId+"/prj_"+projectId+"/"+"tab_data_"+tabId+".txt");
            File fileCol = new File("../tab_file/usr_"+userId+"/prj_"+projectId+"/"+"tab_col_"+tabId+".txt");
            File fileData = new File("../tab_file/usr_"+userId+"/prj_"+projectId+"/"+"tab_data_"+tabId+".txt");

            // crate path of copy file
            File folderTabFile = new File("../tab_file");
            if (!folderTabFile.exists()) folderTabFile.mkdir();

            File folderUser = new File(folderTabFile + "/usr_" + userIdLogin);
            if (!folderUser.exists()) folderUser.mkdir();

            File folderProject = new File(folderUser + "/prj_" + projectId);
            if (!folderProject.exists()) folderProject.mkdir();

            //select tab insert to get new tab id
            int newTabId = 0;
            List<Tab> tabsNew = tr.findByProjectIdAndTabNameAndUserIdAndIsDeleteNotQuery(projectId, tabName, userIdLogin);
            for(int a = 0;a<tabsNew.size();a++){
                newTabId = tabsNew.get(a).getTabId();
                tabsNew.get(i).setFileFrm("tab_col_"+newTabId+".txt");
                tr.save(tabsNew.get(i));
            }
            File fileCopy = new File(folderProject+"/tab_col_"+newTabId+".txt");
            File fileCopyData = new File(folderProject+"/tab_data_"+newTabId+".txt");

            try {
                //create copy file
                fileCopy.createNewFile();
                fileCopyData.createNewFile();

                StringBuffer sbCol = new StringBuffer();
                String textData;
                FileReader fr = new FileReader(fileCol);
                BufferedReader br = new BufferedReader(fr);
                while ((textData = br.readLine()) != null) {
                    sbCol.append(textData+"\r\n");
                }

                StringBuffer sbData = new StringBuffer();
                String textDataData;
                FileReader frData = new FileReader(fileData);
                BufferedReader brData = new BufferedReader(frData);
                while ((textDataData = brData.readLine()) != null) {
                    sbData.append(textDataData+"\r\n");
                }

                //write data tab column
                writeCol(folderProject.toString(), newTabId, sbCol.toString());

                //write data tab data
                File file = new File(fileCopyData.toString());
                String str = sbData.toString();
                FileOutputStream outputStream = new FileOutputStream(file);
                byte[] strToBytes = str.getBytes();
                outputStream.write(strToBytes);
                outputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public String editTab(int tabId, String tabName, String privacy){
        List<Tab> tabs = tr.findByTabId(tabId);
        for(int i=0;i<tabs.size();i++){
            tabs.get(i).setTabName(tabName);
            tabs.get(i).setPrivacy(privacy);
            tr.save(tabs.get(i));
        }
        return null;
    }

    public String editTabData(String data, int seq, int tabId){
        List<Tab> tabs = tr.findByTabId(tabId);
        for(int i = 0;i<tabs.size();i++) {
            int projectId = tabs.get(i).getProjectId();
            int userIdTab = tabs.get(i).getCreatedBy();
            File folderTabFile = new File("../tab_file");
            if (!folderTabFile.exists()) folderTabFile.mkdir();

            File folderUser = new File(folderTabFile + "/usr_" + userIdTab);
            if (!folderUser.exists()) folderUser.mkdir();

            File folderProject = new File(folderUser + "/prj_" + projectId);
            if (!folderProject.exists()) folderProject.mkdir();
            File file = new File(folderProject + "/" + "tab_data_" + tabId + ".txt");
            try {
                StringBuffer sb = new StringBuffer();
                String textData;
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                int iseq = 0;
                while ((textData = br.readLine()) != null) {
                    if(iseq == seq){
                        sb.append(data+"\r\n");
                    }else {
                        sb.append(textData+"\r\n");
                    }
                    iseq++;
                }
                br.close();
                rewrite(file, sb);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String deleteData(int seq, int tabId){
        System.out.println(tabId);
        List<Tab> tabs = tr.findByTabId(tabId);
        for(int i = 0;i<tabs.size();i++) {
            int projectId = tabs.get(i).getProjectId();
            int userIdTab = tabs.get(i).getCreatedBy();
            File folderTabFile = new File("../tab_file");
            if (!folderTabFile.exists()) folderTabFile.mkdir();

            File folderUser = new File(folderTabFile + "/usr_" + userIdTab);
            if (!folderUser.exists()) folderUser.mkdir();

            File folderProject = new File(folderUser + "/prj_" + projectId);
            if (!folderProject.exists()) folderProject.mkdir();
            File file = new File(folderProject + "/" + "tab_data_" + tabId + ".txt");
            try {
                StringBuffer sb = new StringBuffer();
                String textData;
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                int iseq = 0;
                while ((textData = br.readLine()) != null) {
                    if(iseq == seq){
                        System.out.println("replace data to delete character");
                        String str = "";
                        sb.append(str);
                    }else {
                        sb.append(textData+"\r\n");
                    }
                    iseq++;
                }
                br.close();
                rewrite(file, sb);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void rewrite(File file, StringBuffer sb){
        try{
            FileWriter fstream = new FileWriter(file);
            BufferedWriter outobj = new BufferedWriter(fstream);
            outobj.write(sb.toString());
            outobj.close();
        }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void editTableTab(int tabId, String col, List<Integer> delCol){
        List<Tab> tabs = tr.findByTabId(tabId);
        for(int i = 0;i<tabs.size();i++) {
            int projectId = tabs.get(i).getProjectId();
            int userIdCr = tabs.get(i).getCreatedBy();
            File folderTabFile = new File("../tab_file");
            if (!folderTabFile.exists()) folderTabFile.mkdir();

            File folderUser = new File(folderTabFile + "/usr_" + userIdCr);
            if (!folderUser.exists()) folderUser.mkdir();

            File folderProject = new File(folderUser + "/prj_" + projectId);
            if (!folderProject.exists()) folderProject.mkdir();

            writeCol(folderProject.toString(), tabId, col);
            editTableTabDataCol(folderProject.toString(), tabId, delCol, col);
        }
    }

    public void writeCol(String folderProject, int tabId, String col){
        try {
            File file = new File(folderProject + "/" + "tab_col_" + tabId + ".txt");
            file.createNewFile();
            String str = col;
            FileOutputStream outputStream = new FileOutputStream(file);
            byte[] strToBytes = str.getBytes();
            outputStream.write(strToBytes);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editTableTabDataCol(String folderProject, int tabId, List<Integer> delCol, String colHeader){
        try {
            File file = new File(folderProject + "/" + "tab_data_" + tabId + ".txt");
            StringBuffer sb = new StringBuffer();
            String textData;
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            //current length of column of row before update data file
            int currentLengt = 0;

            int x = 0;
            while ((textData = br.readLine()) != null) {
                JsonObject jsonObject = new JsonParser().parse(textData).getAsJsonObject();
                JsonObject jsonObjectHeader = new JsonParser().parse(colHeader).getAsJsonObject();
                x++;
                if(x == 1){
                    currentLengt = jsonObject.size();
                }

                for(int i = 0;i<delCol.size();i++){
                    jsonObject.remove(delCol.get(i).toString());
                }

                //seq col is set key jsonObject1
                int seqCol = 0;
                JsonObject jsonObject1 = new JsonObject();
                for(int a = 0;a<currentLengt;a++){
                    Boolean valJsonObject = (jsonObject.get(String.valueOf(a)) == null) ? false : true;
                    if(valJsonObject){
                        jsonObject1.add(String.valueOf(seqCol), jsonObject.get(String.valueOf(a)));
                        seqCol++;
                    }
                }


                //add colum row data
                int colToAdd = jsonObjectHeader.size();
                int colAdd = jsonObject1.size();
                if(colToAdd > colAdd){
                    for(int i = colAdd;i < colToAdd;i++){
                        System.out.println("key add column => "+i);
                        jsonObject1.addProperty(String.valueOf(i), "");
                    }
                    System.out.println(jsonObject1.toString());
                }
                sb.append(jsonObject1.toString()+"\r\n");
            }
            rewrite(file, sb);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String deleteTable(int tabId){
        List<Tab> tabs = tr.findByTabId(tabId);
        for(int i = 0;i<tabs.size();i++) {
            int projectId = tabs.get(i).getProjectId();
            int userIdCr = tabs.get(i).getCreatedBy();
            File folderTabFile = new File("../tab_file");
            if (!folderTabFile.exists()) folderTabFile.mkdir();

            File folderUser = new File(folderTabFile + "/usr_" + userIdCr);
            if (!folderUser.exists()) folderUser.mkdir();

            File folderProject = new File(folderUser + "/prj_" + projectId);
            if (!folderProject.exists()) folderProject.mkdir();

            File fileData = new File(folderProject + "/" + "tab_data_" + tabId + ".txt");
            File fileColumn = new File(folderProject + "/" + "tab_col_" + tabId + ".txt");

            fileData.delete();
            fileColumn.delete();
        }
        return null;
    }
}

