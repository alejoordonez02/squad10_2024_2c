package com.carga_horaria.carga_horaria;  

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "classpath:com/carga_horaria/carga_horaria/resources",
    glue = "com.carga_horaria.carga_horaria",  
    plugin = {"pretty", "html:target/cucumber-reports"}  
)
public class CucumberTest {}
