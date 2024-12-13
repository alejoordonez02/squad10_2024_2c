package com.carga_horaria.carga_horaria;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import io.cucumber.junit.platform.engine.Cucumber;

@CucumberContextConfiguration
@SpringBootTest(classes = CargaHorariaApplication.class) 
@CucumberOptions(
    features = "src/test/resources", 
    glue = "com.carga_horaria.carga_horaria", 
    plugin = {"pretty", "html:target/cucumber-reports"}
)
public class CucumberSpringConfiguration {}
