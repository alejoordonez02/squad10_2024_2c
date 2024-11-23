package com.carga_horaria.carga_horaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.carga_horaria.carga_horaria.model")
public class CargaHorariaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CargaHorariaApplication.class, args);
    }

}
