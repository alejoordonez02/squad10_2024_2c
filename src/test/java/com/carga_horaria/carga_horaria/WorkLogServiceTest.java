package com.carga_horaria.carga_horaria;

import com.carga_horaria.carga_horaria.model.Task;
import com.carga_horaria.carga_horaria.model.WorkLog;
import com.carga_horaria.carga_horaria.repository.WorkLogRepository;
import com.carga_horaria.carga_horaria.service.EmployeeService;
import com.carga_horaria.carga_horaria.service.RoleService;
import com.carga_horaria.carga_horaria.service.TaskService;
import com.carga_horaria.carga_horaria.service.WorkLogService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class WorkLogServiceTest {

    @Mock
    private WorkLogRepository workLogRepository;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private RoleService roleService;

    @Mock
    private TaskService taskService;

    @InjectMocks
    private WorkLogService workLogService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetWorkedHours() {
        String projectId = "project1";
        String roleName = "Developer";
        String roleExperience = "Senior";
        int year = 2024;
        int month = 2;

        List<String> employeeIds = List.of("emp1", "emp2");
        List<String> taskIds = List.of("task1", "task2");
        WorkLog workLog1 = new WorkLog();
        workLog1.setEmployeeId("emp1");
        workLog1.setTaskId("task1");
        workLog1.setHours(5.0);
        workLog1.setDate(LocalDate.of(year, month, 1));

        WorkLog workLog2 = new WorkLog();
        workLog2.setEmployeeId("emp2");
        workLog2.setTaskId("task2");
        workLog2.setHours(3.0);
        workLog2.setDate(LocalDate.of(year, month, 2));

        WorkLog workLog3 = new WorkLog();
        workLog3.setEmployeeId("emp1");
        workLog3.setTaskId("task3");
        workLog3.setHours(2.0);
        workLog3.setDate(LocalDate.of(year, month, 3));

        List<WorkLog> workLogs = List.of(workLog1, workLog2, workLog3);

        when(roleService.getEmployeeIds(roleName, roleExperience)).thenReturn(employeeIds);
        when(taskService.getTasks(projectId, employeeIds)).thenReturn(taskIds.stream().map(id -> {
            Task task = new Task();
            task.setId(id);
            task.setProjectId(projectId);
            return task;
        }).collect(Collectors.toList()));
        when(workLogRepository.findWorkLog(year, month)).thenReturn(workLogs);

        double totalHours = workLogService.getWorkedHours(projectId, roleName, roleExperience, year, month);

        assertEquals(8.0, totalHours);
    }

}