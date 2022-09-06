package com.cmc;

import com.cmc.service.seeds.DatabaseSeedingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class CmcApplication {

    @Autowired
    DatabaseSeedingService databaseSeedingService;

    private static final Logger logger = LoggerFactory.getLogger(CmcApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CmcApplication.class, args);
    }


    @EventListener
    public void seed(ContextRefreshedEvent event) {
        logger.debug("Start-up application, Seeding database...");

    //    databaseSeedingService.run();
    }


}
