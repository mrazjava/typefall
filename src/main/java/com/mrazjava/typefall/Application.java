package com.mrazjava.typefall;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

//@SpringBootApplication
public class Application {

	private static Logger log = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) {
		log.info("hello 1");
		log.debug("hello 2");
		SpringApplication.run(Application.class, args);
		log.debug("hello 2a");
		log.debug("bye 3");
		log.info("bye 4");
	}
	
	//@Bean
    ApplicationRunner applicationRunner(
    		Environment environment,
    		@Value("${logging.level.com.mrazjava:UNDEFINED}") String logComMrazjava) {
        return args -> {
            log.info("LOG LEVEL (com.mrazjava): " + logComMrazjava);
        };
    }
}
