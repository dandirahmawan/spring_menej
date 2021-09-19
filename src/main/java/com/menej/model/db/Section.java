package com.menej.model.db;

import com.menej.model.view.ViewModule;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "section")
@Getter
@Setter
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "project_id")
    Integer projectId;

    String section;

    @OneToMany(mappedBy = "sectionModule", cascade = CascadeType.ALL)
    @OrderBy("id asc")
	private Set<ViewModule> sectionModule;
}
