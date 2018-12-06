package com.songwie.hadoop;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.songwie.hadoop.base.MogoContants;


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
			
			System.err.println("# hadoop.defaultFS ：" + env.getProperty("hadoop.defaultFS"));
			
			System.err.println("# hadoop.sqoop.address ：" + env.getProperty("hadoop.sqoop.address"));
			System.err.println("# hadoop.sqoop.hdfsConnectorId ：" + env.getProperty("hadoop.sqoop.hdfsConnectorId"));
			System.err.println("# hadoop.sqoop.mysqlConnectorId ：" + env.getProperty("hadoop.sqoop.mysqlConnectorId"));

			MogoContants.setHADOOP_HOST(env.getProperty("hadoop.defaultFS"));
			MogoContants.setHADOOP_INPUT_PATH(env.getProperty("hadoop.input.path"));
			MogoContants.setHADOOP_OUTPUT_PATH(env.getProperty("hadoop.output.path"));

			MogoContants.setSQOOP_HOST(env.getProperty("hadoop.sqoop.address"));
			MogoContants.setSQOOP_HDFS_COONECTOR_ID(env.getProperty("hadoop.sqoop.hdfsConnectorId"));
			MogoContants.setSQOOP_MYSQL_COONECTOR_ID(env.getProperty("hadoop.sqoop.mysqlConnectorId"));
			MogoContants.setSQOOP_MYSQL_URL(env.getProperty("datasource.druid.url"));
			MogoContants.setSQOOP_MYSQL_USERNAME(env.getProperty("datasource.druid.username"));
			MogoContants.setSQOOP_MYSQL_PASSWORD(env.getProperty("datasource.druid.password"));
			MogoContants.setSQOOP_MYSQL_DRIVER_NAME(env.getProperty("datasource.druid.driverClassName"));
			
			MogoContants.setSQOOP_MYSQL_RENTER_SEARCH_TABLE(env.getProperty("hadoop.hdfs2mysql.rentersearch.table"));
			MogoContants.setSQOOP_MYSQL_RENTER_SEARCH_COLUMN(env.getProperty("hadoop.hdfs2mysql.rentersearch.columns"));

		}
		 
	}

}
