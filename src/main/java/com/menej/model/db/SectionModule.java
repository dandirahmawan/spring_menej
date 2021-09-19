package com.menej.model.db;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "section")
public class SectionModule {
    @Id
    Integer id;

    @Column(name = "project_id")
    Integer projectId;

    String section;
}
