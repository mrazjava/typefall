package com.mrazjava.typefall;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
public class Application {

	private static Logger log = LoggerFactory.getLogger(Application.class);
	
	@Autowired
	private Viewer viewer;
	
	public static void main(String[] args) throws Exception {

		ApplicationContext ctx = SpringApplication.run(Application.class, args);
		
		String applicationName = ctx.getEnvironment().getProperty("spring.application.name");
		log.info("initializing {} ...", applicationName);
		
		Application typeFall = ctx.getBean(Application.class);
		typeFall.start();
		
		log.info("exiting {} ...", applicationName);
	}
	
	void start() throws Exception {
		viewer.start();
		viewer.stop();
	}
}
