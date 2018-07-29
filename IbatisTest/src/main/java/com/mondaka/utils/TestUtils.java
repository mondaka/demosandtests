package com.mondaka.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class TestUtils {
	
    private static final Logger LOGGER = LogManager.getLogger(TestUtils.class);


	private static Properties prop = new Properties();
	static {
		try {
			prop.load(TestUtils.class.getClassLoader().getResourceAsStream("conf/config.properties"));
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static void initializeDb() {
		Connection firstConnection;
		try {
			firstConnection = DriverManager.getConnection(prop.getProperty("jdbc.url"),
					prop.getProperty("jdbc.username"), prop.getProperty("jdbc.password"));
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
		try {
			Statement statement = firstConnection.createStatement();
			try {
				statement.execute("CREATE TABLE ibatistest_tbl (id INT NOT NULL," + 
						"   title VARCHAR(50) NOT NULL," + 
						"   author VARCHAR(20) NOT NULL," + 
						"   submission_date DATE," + 
						"   PRIMARY KEY (id)" + 
						");");
			} finally {
				if(statement != null) {
					statement.close();	
				}
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if(firstConnection != null) {
				try {
					firstConnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e.getMessage(), e);
				}	
			}
		}
	}
}
