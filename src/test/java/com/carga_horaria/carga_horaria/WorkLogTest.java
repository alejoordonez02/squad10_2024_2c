package com.carga_horaria.carga_horaria;

import com.carga_horaria.carga_horaria.model.WorkLog;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class WorkLogTest {

    @Test
    void testGetAndSetHours() {
        WorkLog workLog = new WorkLog();
        double hours = 8.0;
        workLog.setHours(hours);
        assertEquals(hours, workLog.getHours(), 0.01);
    }

    @Test
    void testGetAndSetTaskId() {
        WorkLog workLog = new WorkLog();
        String taskId = "task1";
        workLog.setTaskId(taskId);
        assertEquals(taskId, workLog.getTaskId());
    }

    @Test
    void testGetAndSetDate() {
        WorkLog workLog = new WorkLog();
        LocalDate date = LocalDate.of(2024, 11, 17);
        workLog.setDate(date);
        assertEquals(date, workLog.getDate());
    }

    @Test
    void testGetAndSetEmployeeId() {
        WorkLog workLog = new WorkLog();
        String employeeId = "employee1";
        workLog.setEmployeeId(employeeId);
        assertEquals(employeeId, workLog.getEmployeeId());
    }
}
