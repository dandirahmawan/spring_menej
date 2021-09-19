package com.menej.repo;

import com.menej.model.view.ViewTabRelationData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ViewTabRelationDataRepo extends JpaRepository<ViewTabRelationData, String> {
    List<ViewTabRelationData> findByFunctionTextIn(List<String> functionText);
}
