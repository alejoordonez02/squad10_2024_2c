package com.carga_horaria.carga_horaria.repository;

import com.carga_horaria.carga_horaria.model.WorkLog;
import java.time.LocalDate;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkLogRepository extends JpaRepository<WorkLog, Long> {
    @Query("SELECT w FROM WorkLog w WHERE w.employee_id = :employeeId AND YEAR(w.date) = :year AND MONTH(w.date) = :month")
    List<WorkLog> findWorkLog(@Param("employeeId") String employeeId, 
                              @Param("year") int year, 
                              @Param("month") int month);
    @Query("SELECT w FROM WorkLog w WHERE YEAR(w.date) = :year AND MONTH(w.date) = :month")
    List<WorkLog> findWorkLog(@Param("year") int year, 
                              @Param("month") int month);

    @Query("SELECT w FROM WorkLog w WHERE w.employee_id = :employeeId AND w.date BETWEEN :startDate AND :endDate")
    List<WorkLog> findWorkLogsByEmployeeAndDateRange(@Param("employeeId") String employeeId, 
                                                 @Param("startDate") LocalDate startDate, 
                                                 @Param("endDate") LocalDate endDate);

}
