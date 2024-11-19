package com.carga_horaria.carga_horaria;

import com.carga_horaria.carga_horaria.model.WorkLog;
import com.carga_horaria.carga_horaria.model.Task;

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
    void testGetAndSetTask() {
        WorkLog workLog = new WorkLog();
        Task task = new Task();
        workLog.setTask(task);
        assertEquals(task, workLog.getTask());
    }

    @Test
    void testGetAndSetDate() {
        WorkLog workLog = new WorkLog();
        LocalDate date = LocalDate.of(2024, 11, 17);
        workLog.setDate(date);
        assertEquals(date, workLog.getDate());
    }

}
