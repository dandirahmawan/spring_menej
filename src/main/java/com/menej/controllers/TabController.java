package com.menej.controllers;

import com.menej.Utils;
import com.menej.model.DataTab;
import com.menej.model.db.ProjectTeam;
import com.menej.model.db.Tab;
import com.menej.model.TabPage;
import com.menej.model.db.UserPrivacyTab;
import com.menej.model.view.ViewTab;
import com.menej.model.view.ViewTabRelationData;
import com.menej.repo.*;
import com.menej.service.TabService;
import com.menej.service.UserPrivacyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RestController
public class TabController {
    @Autowired
    TabService ts;

    @Autowired
    TabRepo tr;

    @Autowired
    ProjectTeamRepo projectTeamRepo;

    @Autowired
    UserPrivacyService ups;

    @Autowired
    ViewTabRepo vtr;

    @Autowired
    UserPrivacyTabRepo uptr;

    @PostMapping("/new_tab")
    public String newTab(String sessionId, int userId, String tabName, int projectId, String prv){
        Utils utils = new Utils();
        Boolean isAccess = utils.getAccess(userId, sessionId);
        String rtn = null;
        if(isAccess){
            rtn = ts.newTab(projectId, tabName, userId, prv);
        }
        return rtn;
    }

    @PostMapping("/tab_list")
    public List<DataTab> getListTab(int projectId, int userId, String sessionId){
        Utils utils = new Utils();
        Boolean isAccess = utils.getAccess(userId, sessionId);
        List<DataTab> tabs = new ArrayList<DataTab>();
        if(isAccess){
            tabs = tr.findByProjectIdQuery(projectId, userId);
        }
        return tabs;
    }

    @PostMapping("/tab_page")
    public List<TabPage> tabPage(int userId, int tabId, String sessionId, int projectId){
        List<TabPage> tabPages = new ArrayList<TabPage>();
        TabPage tabPage = new TabPage();

        ViewTab tab = vtr.findByTabIdQuery(userId, tabId);
        String frm = "";
        String data = "";
        Boolean tableReady = false;
        File file = null;
        tabPage.setTab(tab);

        /*set data privacy tab*/
        List<UserPrivacyTab> userPrivacyTabList = uptr.findByTabId(tabId);
        tabPage.setUserPrivacyTab(userPrivacyTabList);

        if(tab != null){
            frm = (tab.getFileFrm() == null) ? "" : tab.getFileFrm();
            int createdBy = tab.getCreatedBy();

            if(!frm.equals("")){
                file = new File("../tab_file/usr_"+createdBy+"/prj_"+projectId+"/tab_col_"+tabId+".txt");
                if(file.exists()) tableReady = true;
            }
        }

        if(!tableReady){
            data = "no data";
        }else{
            try{
                Scanner myReader = new Scanner(file);
                while (myReader.hasNextLine()) {
                    data = myReader.nextLine();
                }
                myReader.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        //set column table tab page
        tabPage.setColumnTab(data);

        /*get row tab data*/
        JSONObject jsonObject = ts.rowTab(tabId);
        tabPage.setRowTab(jsonObject.toString());

        /*add all data tab page*/
        tabPages.add(tabPage);

        return tabPages;
    }

    @PostMapping("/new_table")
    public String createTable(int userId, String sessionId, int projectId, int tabId, String col){
        File folderTabFile = new File("../tab_file");
        if(!folderTabFile.exists()) folderTabFile.mkdir();

        File folderUser = new File(folderTabFile+"/usr_"+userId);
        if(!folderUser.exists()) folderUser.mkdir();

        File folderProject = new File(folderUser+"/prj_"+projectId);
        if(!folderProject.exists()) folderProject.mkdir();

        try{
            File file = new File(folderProject+"/"+"tab_col_"+tabId+".txt");
            file.createNewFile();
            String str = col;
            FileOutputStream outputStream = new FileOutputStream(file);
            byte[] strToBytes = str.getBytes();
            outputStream.write(strToBytes);
            outputStream.close();
            ts.createTable(tabId, "tab_col_"+tabId+".txt");
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @PostMapping("/new_tab_data")
    public String newTabData(int userId, String sessionId, int tabId, String data){
        Utils utils = new Utils();
        Boolean isAccess = utils.getAccess(userId, sessionId);
        if(isAccess){
            ts.newTabData(tabId, data);
        }
        return null;
    }

    @PostMapping("/edit_tab")
    public String editTab(int userId, String sessionId, int tabId, String tabName, String prv, String userSelectedPrivacy){
        Utils utils = new Utils();
        Boolean isAccess = utils.getAccess(userId, sessionId);
        if(isAccess){
            ts.editTab(tabId, tabName, prv);

            //delete user privacy selected
            ups.deleteUserPrivacyByTabId(tabId);

            //insert new user privacy data
            if(!userSelectedPrivacy.equals("")){
                String[] userSelecteds = userSelectedPrivacy.split(",");
                for(int i = 0;i<userSelecteds.length;i++){
                    if(!userSelecteds[i].equals("")){
                        int userIdInt = Integer.parseInt(userSelecteds[i]);
                        ups.insertUserPrivacy(userIdInt, tabId);
                    }
                }
            }
        }
        return null;
    }

    @PostMapping("/edit_table")
    public String editTable(String sessionId, int userId, String col ,int tabId, String re_col){
        String[] strings = re_col.split(",");
        List<Integer> delCol = new ArrayList<Integer>();
        if(!re_col.equals("")) {
            for (String item : strings) {
                delCol.add(Integer.valueOf(item));
            }
        }
        ts.editTableTab(tabId, col, delCol);
        return null;
    }

    @PostMapping("/delete_tab_table")
    public String deleteTabTable(int tabId, String sessionId, int userId){
        Utils utils = new Utils();
        Boolean isAccess = utils.getAccess(userId, sessionId);
        if(isAccess){
            ts.deleteTable(tabId);
        }
        return null;
    }

    @PostMapping("/copy_tab")
    public String copyTab(int userId, String sessionId, int tabId, String tabName, int projectId, String confirmReplace){
        Utils utils = new Utils();
        Boolean isAccess = utils.getAccess(userId, sessionId);
        Boolean canCopy = true;
        String code = "0";
        if(isAccess){
            List<ProjectTeam> projectTeams =
                    projectTeamRepo.findByProjectIdAndUserIdAndUserStatusQry(projectId, userId, "Y");

            //check user is team member of project
            if(projectTeams.size() == 0){
                canCopy = false;
                code = "1";
            }

            List<Tab> tabs = tr.findByProjectIdAndTabNameAndUserIdAndIsDeleteNotQuery(projectId, tabName, userId);
            if(tabs.size() > 0 && !confirmReplace.equals("Y")){
                for(int i = 0;i<tabs.size();i++){
                    String isDel = tabs.get(i).getIsDelete() == null ? "N" : tabs.get(i).getIsDelete();
                    if(!isDel.equals("Y")){
                        code = "2";
                        canCopy = false;
                    }
                }
            }else{
                for(int i = 0;i<tabs.size();i++){
                    String isDel = tabs.get(i).getIsDelete() == null ? "N" : tabs.get(i).getIsDelete();
                    System.out.println(tabs.get(i).getCreatedBy()+" === "+userId+" -- "+isDel);
                    if(tabs.get(i).getCreatedBy() != userId && !isDel.equals("Y")){
                        code = "3";
                        canCopy = false;
                    }
                }
            }

            if(canCopy){
                Boolean isReplace = (confirmReplace.equals("Y")) ? true : false;
                System.out.println("replace is = "+isReplace.toString());
                ts.copyTab(tabId, tabName, userId, isReplace);
            }
        }
        return code;
    }

    @PostMapping("/delete_tab")
    public String deleteTab(String sessionId, int userId, int tabId){
        Utils utils = new Utils();
        String rtn = "";
        Boolean isAccess = utils.getAccess(userId, sessionId);
        if(isAccess){
            List<Tab> tabs = tr.findByTabId(tabId);
            for(int i = 0;i<tabs.size();i++){
                if(tabs.get(i).getCreatedBy() != userId){
                    int projectId = tabs.get(i).getProjectId();
                    int createdBy = tabs.get(i).getCreatedBy();
                    System.out.println("created by is "+createdBy+" - "+projectId);
                    List<ProjectTeam> projectTeams = projectTeamRepo.findByProjectIdAndUserIdAndUserStatusQry(projectId, createdBy, "Y");
                    if(projectTeams.size() == 0){
                        tabs.get(i).setIsDelete("Y");
                        tr.save(tabs.get(i));
                    }else{
                        //code 1 => for user is still a member team project
                        rtn = "1";
                    }
                }else{
                    tabs.get(i).setIsDelete("Y");
                    tr.save(tabs.get(i));
                }
            }
        }
        return rtn;
    }

//    @PostMapping("/row_tab")
//    public JSONObject rowTab(int tabId){
//        List<Tab> tabs = tr.findByTabId(tabId);
//        JSONArray jsonArray = new JSONArray();
//        JSONObject jsonObjectHeader = new JSONObject();
//        jsonObjectHeader.put("tab_data", null);
//        jsonObjectHeader.put("relation_data", null);
//
//        for(int i = 0;i<tabs.size();i++) {
//            List<String> functions = new ArrayList<String>();
//            int projectId = tabs.get(i).getProjectId();
//            int userIdTab = tabs.get(i).getCreatedBy();
//            File folderTabFile = new File("../tab_file");
//            if (!folderTabFile.exists()) folderTabFile.mkdir();
//
//            File folderUser = new File(folderTabFile + "/usr_" + userIdTab);
//            if (!folderUser.exists()) folderUser.mkdir();
//
//            File folderProject = new File(folderUser + "/prj_" + projectId);
//            if (!folderProject.exists()) folderProject.mkdir();
//            File file = new File(folderProject+"/"+"tab_data_"+tabId+".txt");
//            try {
//                Scanner myReader = new Scanner(file);
//                int ii = 0;
//                while (myReader.hasNextLine()) {
//                    String jsonString = myReader.nextLine();
//                    if(ii < 100){
//                        if (jsonString != "") {
//                            JsonObject jsonObject = new JsonParser().parse(jsonString).getAsJsonObject();
//                            for(int x = 0;x<jsonObject.size();x++){
//                                String nameObj = String.valueOf(x);
//                                String val = String.valueOf(jsonObject.get(nameObj));
//                                int l = val.length() - 1;
//                                String val2 = (!val.equals("")) ? val.substring(1, l) : "";
//
//                                //validation function tab
//                                if(!val.equals("") && val.length() > 5){
//                                    if(val2.substring(0,1).equals("=")
//                                            && val2.substring(4, 5).equals("(")
//                                            && val2.substring(val2.length() - 1, val2.length()).equals(")"))
//                                    {
//                                       functions.add(val2);
//                                    }
//                                }
//                            }
//                            jsonArray.add(jsonObject);
//                        }
//                    }
//                    ii++;
//                }
//
//                //get data relation or function
//                List<ViewTabRelationData> relationDataList = vtrdr.findByFunctionTextIn(functions);
//                ObjectMapper mapper = new ObjectMapper();
//                String relationData = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(relationDataList);
//
//                //set tab data and relation data in json object as return controller
//                jsonObjectHeader.put("tab_data", jsonArray);
//                jsonObjectHeader.put("relation_data", relationData);
//                myReader.close();
//            } catch (Exception e) {
//                System.out.println("file not found");
//                e.printStackTrace();
//            }
//        }
//        return jsonObjectHeader;
//    }

    @PostMapping("/edit_tab_data")
    public String editTabData(String data, int seq, int tabId){
        ts.editTabData(data, seq, tabId);
        return null;
    }

    @PostMapping("/delete_tab_data")
    public String editTabData(int seq, int tabId, int userId, String sessionId){
        Utils utils = new Utils();
        Boolean isAccss = utils.getAccess(userId, sessionId);
        if(isAccss) {
            ts.deleteData(seq, tabId);
        }
        return null;
    }

    @GetMapping("/export_excel_tab/{tabId}")
    public String exportExcelTab(HttpServletResponse response, @RequestParam String tab_n, @PathVariable int tabId){
        List<Tab> tab = tr.findByTabIdAndTabName(tabId, tab_n);
        String rtn = "File not found";
        if(tab.size() > 0){
            rtn = "";
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Data");

            Font headerFont = workbook.createFont();
            headerFont.setColor(IndexedColors.BLACK.getIndex());
            headerFont.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);

            Font dataFont = workbook.createFont();
            dataFont.setFontHeight((short) 200);

            // Create a CellStyle with the font
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);
            headerCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            headerCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

            CellStyle dataCellStyle = workbook.createCellStyle();
            dataCellStyle.setWrapText(true);
            dataCellStyle.setVerticalAlignment(CellStyle.VERTICAL_TOP);
            dataCellStyle.setFont(dataFont);

            int userIdCreator = tab.get(0).getCreatedBy();
            String tabCol = tab.get(0).getFileFrm();
            String tabData = "tab_data_"+tab.get(0).getTabId()+".txt";
            int projectId = tab.get(0).getProjectId();

            String folderFileCol = "../tab_file/usr_"+userIdCreator+"/prj_"+projectId+"/"+tabCol;
            String folderFileData = "../tab_file/usr_"+userIdCreator+"/prj_"+projectId+"/"+tabData;

            File fileHeader = new File(folderFileCol);
            File fileData = new File(folderFileData);
            if(fileData.exists() && fileHeader.exists()) {
                int countColumn = 0;
                //read file header
                try{
                    Scanner myReader = new Scanner(fileHeader);
                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        JsonObject jsonObject = new JsonParser().parse(data).getAsJsonObject();
                        countColumn = jsonObject.size() + 1;

                        Row headerRow = sheet.createRow(0);
                        for(int i = 0;i<countColumn;i++){
                            //create cell header
                            int validCell = i - 1;
                            Cell cell = headerRow.createCell(i);
                            cell.setCellStyle(headerCellStyle);
                            if(i == 0){
                                sheet.setColumnWidth(0, 4 * 256);
                                cell.setCellValue("No");
                            }else{
                                sheet.setColumnWidth(i, 28 * 256);
                                String val = jsonObject.get(String.valueOf(validCell)).toString();
                                val = val.substring(1, val.length() - 1);
                                cell.setCellValue(val);
                            }
                        }
                    }
                    myReader.close();
                }catch (Exception e){
                    e.printStackTrace();
                }

                //read file data
                try{
                    Scanner myReaderData = new Scanner(fileData);
                    int row = 0;
                    while (myReaderData.hasNextLine()){
                        row++;
                        String data = myReaderData.nextLine();
                        JsonObject jsonObject = new JsonParser().parse(data).getAsJsonObject();

                        Row dataRow = sheet.createRow(row);
                        for(int i = 0;i<countColumn;i++){
                            int validCellData = i - 1;
                            Cell cell = dataRow.createCell(i);
                            if(i == 0){
                                cell.setCellValue(row);
                                cell.setCellStyle(dataCellStyle);
                            }else{
                                String val = jsonObject.get(String.valueOf(validCellData)).toString();
                                val = val.substring(1, val.length() - 1);
                                cell.setCellValue(val);
                                cell.setCellStyle(dataCellStyle);
                            }
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            try{
                String fileNameExcel = tab_n.replace(" ", "_");
                response.setHeader("Content-disposition", "attachment; filename="+fileNameExcel+".xlsx");
                workbook.write( response.getOutputStream() );
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return rtn;
    }
}
