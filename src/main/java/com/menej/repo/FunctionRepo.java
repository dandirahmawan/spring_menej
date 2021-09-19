package com.menej.repo;

import com.menej.model.FunctionData;
import com.menej.model.db.TempFunctionData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FunctionRepo extends JpaRepository<TempFunctionData, Integer> {
    String sql = "SELECT new com.menej.model.FunctionData" +
                "   ( \n" +
                "         modul.modulId,  \n" +
                "         modul.modulName, \n" +
                "         'mod', \n" +
                "         '' \n"+
                "    ) \n" +
                "FROM Modul modul \n" +
                "WHERE modul.projectId = ?1 \n" +
                "AND (modul.isTrash IS NULL OR modul.isTrash != 'Y')";
    @Query(sql)
    List<FunctionData> findAllModQry(int projectId);

    String sql1 = "SELECT new com.menej.model.FunctionData( \n" +
            "   docFile.id, \n" +
            "   docFile.fileName, \n" +
            "   'doc', \n" +
            "   docFile.path \n"+
            ") \n" +
            "FROM DocumentFile docFile \n" +
            "JOIN Modul modul ON modul.modulId = docFile.modulId \n" +
            "WHERE modul.projectId = ?1 \n" +
            "AND (modul.isTrash IS NULL OR modul.isTrash != 'Y')";
    @Query(sql1)
    List<FunctionData> findAllDocQry(int projectId);

//    String sqlUnion = "SELECT new FunctionData(rst.id, rst.name, rst.type) FROM \n" +
//                        "( \n "+
//                            "SELECT new FunctionData( \n"+
//                            "   modul.modulId AS id, \n" +
//                            "   modul.modulName AS name, \n" +
//                            "   'mod' AS type \n" +
//                            ") \n" +
//                            "FROM Modul modul \n" +
//                            "WHERE modul.projectId = ?1 \n" +
//                            "AND (modul.isTrash IS NULL OR modul.isTrash != 'Y') " +
//                            "UNION \n"+
//                            "SELECT new FunctionData( \n" +
//                             "   docFile.id AS id, \n" +
//                             "   docFile.fileName AS name, \n" +
//                             "   'doc' AS type \n"+
//                            "( \n" +
//                            "FROM DocumentFile docFile \n" +
//                            "JOIN Modul modul ON modul.modulId = docFile.modulId \n" +
//                            "WHERE modul.projectId = ?1 \n" +
//                            "AND (modul.isTrash IS NULL OR modul.isTrash != 'Y') \n" +
//                        ") rst";
//    @Query(sqlUnion)
//    List<FunctionData> findAllUnion(int projectId);
}
