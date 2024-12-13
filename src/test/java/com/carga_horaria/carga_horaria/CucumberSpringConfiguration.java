package com.carga_horaria.carga_horaria;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.carga_horaria.carga_horaria.repository.WorkLogRepository;
import com.carga_horaria.carga_horaria.service.WorkLogService;

@CucumberContextConfiguration
@SpringBootTest(classes = CargaHorariaApplication.class,
                properties = {
                    "spring.datasource.url=none",
                    "spring.datasource.driverClassName=none",
                    "spring.jpa.hibernate.ddl-auto=none",
                    "spring.datasource.initialize=false",
                    "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"
}) 
public class CucumberSpringConfiguration {
    @MockBean
    private WorkLogService workLogService; 

    @MockBean
    private WorkLogRepository workLogRepository;
    
}
