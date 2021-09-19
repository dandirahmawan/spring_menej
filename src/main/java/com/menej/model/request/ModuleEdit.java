package com.menej.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModuleEdit {
    String assigned;
    String checklist;
    String date;
    String desc;
    String labelModule;
    Integer modulId;
    String moduleName;
    Integer section;
    Integer status;
    String projectId;
    Integer createdBy;
}
