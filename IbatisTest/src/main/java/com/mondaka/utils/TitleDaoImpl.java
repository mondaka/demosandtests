package com.mondaka.utils;

import java.util.Date;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.mondaka.test.ibatis.entities.Title;

@SuppressWarnings("deprecation")
public class TitleDaoImpl extends SqlMapClientDaoSupport implements TitleDao {

	//FIXME: Not Thread Safe
	private static int idTitle = 1;
	
	@Override
	public void insertNewTitle(String author, String title, Date submissionDate) {

		Title newTitle = new Title();
		
		newTitle.setAuthor(author);
		newTitle.setId(idTitle++);
		newTitle.setTitle(title);
		newTitle.setSubmissionDate(submissionDate);
		
		getSqlMapClientTemplate().insert("insertTitle", newTitle);
	}

	@Override
	public Title getTitleById(int id) {
		return (Title) getSqlMapClientTemplate().queryForObject("selectTitleById", id);
	}
}
