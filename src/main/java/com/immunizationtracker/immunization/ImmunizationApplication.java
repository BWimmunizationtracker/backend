package com.immunizationtracker.immunization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableWebMvc
@EnableJpaAuditing
@SpringBootApplication
@EnableSwagger2
public class ImmunizationApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(ImmunizationApplication.class, args);
    }

}
