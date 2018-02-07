package com.sp17.dao;

import java.util.List;

import com.sp17.domain.FormSubmission;

public interface FormSubmissionDao {
	
	//void create(Integer id, Integer formId, Integer serviceLevelId, String customerEmail, String textTrademark, List<Term> terms) ;
	
	void createFormSubmission(FormSubmission formsubmission);
	
	void createFormSubmissionTerm(FormSubmission formsubmission);	
	
	FormSubmission getFormSubmission(Integer id);
	
	List<FormSubmission> listFormSubmission();
	
	void delete(Integer id);
	
	void update(Integer id, Integer formId, Integer serviceLevelId, String customerEmail, String textTrademark);
	
	void update(FormSubmission formsubmission);
	
	void deleteFormSubmissionTerms(FormSubmission formsubmission);
	

}
