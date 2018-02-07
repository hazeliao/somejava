package com.sp17.dao;

import java.util.List;
import java.util.*;


import com.sp17.domain.FormSubmissionHistory;
import com.sp17.domain.Term;

public interface FormSubmissionHistoryDao {
	
	//void create(Integer id, Integer formId, Integer serviceLevelId, String customerEmail, String textTrademark, List<Term> terms) ;
	
	void createFormSubmissionHistory(FormSubmissionHistory formsubmissionhistory);
	
	FormSubmissionHistory getFormSubmissionHistory(Integer id);
	
	FormSubmissionHistory getLatestHistory(Integer id);
	
	List<FormSubmissionHistory> listFormSubmissionHistory();
	
	List<FormSubmissionHistory> listHistoryBySubmissionId(int id);
	
	void delete(Integer id);
	
	void update(long id, Date dateAndTime, String empName, long formSubmissionId, String description, long eventTypeId);

}
