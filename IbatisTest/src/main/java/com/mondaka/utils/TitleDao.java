package com.mondaka.utils;

import java.util.Date;

import com.mondaka.test.ibatis.entities.Title;

public interface TitleDao {
	
	public void insertNewTitle(String author, String title, Date submissionDate);
	
	public Title getTitleById(int id);
}
