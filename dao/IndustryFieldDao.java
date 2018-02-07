package com.sp17.dao;

import java.util.List;

import com.sp17.domain.IndustryField;


public interface IndustryFieldDao {
	
	void create(int id, String name, int priority);
	
	IndustryField getIndustryField(int id);
	
	List<IndustryField> listIndustryFields();
	
	void delete(int id);
	
	void update(int id, String name);
}
