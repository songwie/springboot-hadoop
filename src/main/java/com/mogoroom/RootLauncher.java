package com.mogoroom;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.mogoroom.base.MogoContants;


@Component
public class RootLauncher implements CommandLineRunner{

	@Autowired(required = true)
	private ConfigurableApplicationContext context;
	
	@Autowired
    private Environment env;

	@Override
	public void run(String... args) throws Exception {
		String envs[] = env.getActiveProfiles();
		System.err.println("#" + env);
		for(String proc : envs){
			System.err.println("# current environment : " + proc);
			System.err.println("# database ：" + env.getProperty("datasource.druid.url"));
			System.err.println("# http port ：" + env.getProperty("server.port"));
			
			MogoContants.setHADOOP_HOST(env.getProperty("hadoop.fs"));;
		}
		 
	}

}
