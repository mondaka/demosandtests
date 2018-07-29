package com.mondaka.init;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mondaka.test.ibatis.entities.Title;
import com.mondaka.utils.TestUtils;
import com.mondaka.utils.TitleDao;

public class InitIbatisTest {
	
	private static final Logger LOGGER = LogManager.getLogger(InitIbatisTest.class);

	public static void main(String[] args) {
		// open/read the application context file
	    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:conf/spring-ibatis.xml");
	    try {
		    LOGGER.info("Contexto inicializado");
			TestUtils.initializeDb();
			LOGGER.info("Base de datos inicializada");
			TitleDao titleDao = ctx.getBean(TitleDao.class);
			titleDao.insertNewTitle("Jose Saramago", "11 Minutos", new Date());
			Title aTitle = titleDao.getTitleById(1);
			LOGGER.info("Titulo recogido: " + aTitle.getTitle());
	    	
	    } finally {
	    	ctx.close();	
	    }
	}
}
