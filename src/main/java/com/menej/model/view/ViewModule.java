package com.menej.model.view;

import com.menej.model.db.AssignedModuleReference;
import com.menej.model.db.LabelModuleReference;
import com.menej.model.db.Section;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "view_module")
public class ViewModule {
	@Id
    @Column(name = "modul_id") 
    private int modulId;
    
	@Column(name = "project_id")
    private int projectId;

    @Column(name = "modul_name")
    private String modulName;
    
    @Column(name = "pic")
    private int pic;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "created_by")
    private int createdBy;

    @Column(name = "updated_by")
    private int updatedBy;

    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "modul_status")
    private String modulStatus;

    @Column(name = "description")
    private String description;

    @Column(name = "is_trash")
    private String isTrash;

    @Column(name = "percentage")
    private String percentage;

    @Column(name = "count_bugs")
    private int countBugs;

    @Column(name = "count_doc_file")
    private int countDoc;

    @Column(name = "count_bugs_close")
    int countBugsClose;

    @Column(name = "section_id", insertable =  false, updatable = false)
    private Integer sectionId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    private Section sectionModule;

    @OneToMany(mappedBy = "viewModule", cascade = CascadeType.ALL)
    private Set<LabelModuleReference> label;

    @OneToMany(mappedBy = "viewModule", cascade = CascadeType.ALL)
    private Set<AssignedModuleReference> assignTo;

    public int getModulId() {
        return modulId;
    }

    public void setModulId(int modulId) {
        this.modulId = modulId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getModulName() {
        return modulName;
    }

    public void setModulName(String modulName) {
        this.modulName = modulName;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getModulStatus() {
        return modulStatus;
    }

    public void setModulStatus(String modulStatus) {
        this.modulStatus = modulStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsTrash() {
        return isTrash;
    }

    public void setIsTrash(String isTrash) {
        this.isTrash = isTrash;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public int getCountBugs() {
        return countBugs;
    }

    public void setCountBugs(int countBugs) {
        this.countBugs = countBugs;
    }

    public int getCountDoc() {
        return countDoc;
    }

    public void setCountDoc(int countDoc) {
        this.countDoc = countDoc;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public int getCountBugsClose() {
        return countBugsClose;
    }

    public void setCountBugsClose(int countBugsClose) {
        this.countBugsClose = countBugsClose;
    }

    public Set<LabelModuleReference> getLabel() {
        return label;
    }

    public void setLabel(Set<LabelModuleReference> label) {
        this.label = label;
    }

    public Set<AssignedModuleReference> getAssignTo() {
        return assignTo;
    }

    public void setAssignTo(Set<AssignedModuleReference> assignTo) {
        this.assignTo = assignTo;
    }
}