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
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


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

    @Test
    void testGetWorkedHoursInWeek() {
        String employeeId = "E001";
        LocalDate startDate = LocalDate.of(2024, 11, 1); 
        LocalDate endDate = LocalDate.of(2024, 11, 7); 
        WorkLog workLog1 = new WorkLog();
        workLog1.setHours(8);
        workLog1.setDate(LocalDate.of(2024, 11, 2)); 

        WorkLog workLog2 = new WorkLog();
        workLog2.setHours(7);
        workLog2.setDate(LocalDate.of(2024, 11, 5)); 

        List<WorkLog> workLogs = Arrays.asList(workLog1, workLog2);

        when(workLogRepository.findWorkLogsByEmployeeAndDateRange(employeeId, startDate, endDate))
                .thenReturn(workLogs);

        double totalHours = workLogService.getWorkedHoursInWeek(employeeId, startDate, endDate);

        assertEquals(15.0, totalHours, "Las horas trabajadas en la semana deberían ser 15.0");
        verify(workLogRepository, times(1)).findWorkLogsByEmployeeAndDateRange(employeeId, startDate, endDate);
    }

    @Test
    void testGetWorkedHoursInWeek_WithLogs_ReturnsCorrectTotal() {
        LocalDate startDate = LocalDate.of(2024, 11, 1); 
        LocalDate endDate = LocalDate.of(2024, 11, 7);   

        WorkLog log1 = new WorkLog();
        log1.setEmployeeId("employee1");
        log1.setDate(LocalDate.of(2024, 11, 2));
        log1.setHours(5);

        WorkLog log2 = new WorkLog();
        log2.setEmployeeId("employee1");
        log2.setDate(LocalDate.of(2024, 11, 4));
        log2.setHours(3);

        List<WorkLog> workLogs = Arrays.asList(log1, log2);

        when(workLogRepository.findWorkLogsByEmployeeAndDateRange("employee1", startDate, endDate)).thenReturn(workLogs);

        double totalHours = workLogService.getWorkedHoursInWeek("employee1", startDate, endDate);

        assertEquals(8.0, totalHours);
    }

    @Test
    void testGetWorkedHoursInWeek_NoLogs_ReturnsZero() {
        LocalDate startDate = LocalDate.of(2024, 11, 1);
        LocalDate endDate = LocalDate.of(2024, 11, 7);

        when(workLogRepository.findWorkLogsByEmployeeAndDateRange("employee1", startDate, endDate)).thenReturn(Arrays.asList());

        double totalHours = workLogService.getWorkedHoursInWeek("employee1", startDate, endDate);

        assertEquals(0.0, totalHours);
    }

    @Test
    void testGetWorkedHoursInWeek_MultipleLogs_ReturnsCorrectTotal() {

        LocalDate startDate = LocalDate.of(2024, 11, 1);
        LocalDate endDate = LocalDate.of(2024, 11, 7);

        WorkLog log1 = new WorkLog();
        log1.setEmployeeId("employee1");
        log1.setDate(LocalDate.of(2024, 11, 2));
        log1.setHours(5);

        WorkLog log2 = new WorkLog();
        log2.setEmployeeId("employee1");
        log2.setDate(LocalDate.of(2024, 11, 4));
        log2.setHours(3);

        WorkLog log3 = new WorkLog();
        log3.setEmployeeId("employee1");
        log3.setDate(LocalDate.of(2024, 11, 5));
        log3.setHours(2);

        List<WorkLog> workLogs = Arrays.asList(log1, log2, log3);

        when(workLogRepository.findWorkLogsByEmployeeAndDateRange("employee1", startDate, endDate)).thenReturn(workLogs);

        double totalHours = workLogService.getWorkedHoursInWeek("employee1", startDate, endDate);

        assertEquals(10.0, totalHours);
    }

    @Test
    void testGetWorkedHoursInWeek_SingleDay_ReturnsCorrectTotal() {
        LocalDate startDate = LocalDate.of(2024, 11, 4);
        LocalDate endDate = LocalDate.of(2024, 11, 4);

        WorkLog log = new WorkLog();
        log.setEmployeeId("employee1");
        log.setDate(LocalDate.of(2024, 11, 4));
        log.setHours(8);

        when(workLogRepository.findWorkLogsByEmployeeAndDateRange("employee1", startDate, endDate))
                .thenReturn(Arrays.asList(log));

        double totalHours = workLogService.getWorkedHoursInWeek("employee1", startDate, endDate);

        assertEquals(8.0, totalHours);
    }

    @Test
    void testGetWorkedHoursInWeek_LimitDates_ReturnsCorrectTotal() {
        LocalDate startDate = LocalDate.of(2024, 11, 3);
        LocalDate endDate = LocalDate.of(2024, 11, 9);   

        WorkLog log1 = new WorkLog();
        log1.setEmployeeId("employee1");
        log1.setDate(LocalDate.of(2024, 11, 3));
        log1.setHours(4);

        WorkLog log2 = new WorkLog();
        log2.setEmployeeId("employee1");
        log2.setDate(LocalDate.of(2024, 11, 7));
        log2.setHours(6);

        List<WorkLog> workLogs = Arrays.asList(log1, log2);

        when(workLogRepository.findWorkLogsByEmployeeAndDateRange("employee1", startDate, endDate))
                .thenReturn(workLogs);

        double totalHours = workLogService.getWorkedHoursInWeek("employee1", startDate, endDate);

        assertEquals(10.0, totalHours);
    }

}