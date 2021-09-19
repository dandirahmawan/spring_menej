package com.menej.model.request;

import com.menej.model.view.ViewBugs;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ModuleParamEdit {
    ModuleEdit module;
    List<ViewBugs> checklist;
}

