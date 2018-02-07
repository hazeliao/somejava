package com.sp17.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.sp17.dao.FormSubmissionDao;
import com.sp17.domain.FormSubmission;
import com.sp17.domain.Term;
import com.sp17.mapper.FormSubmissionMapper;
import com.sp17.mapper.TermMapper;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

@Component
public class FormSubmissionDaoImpl implements FormSubmissionDao{
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		  this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	 }
	/*
	@Override
	public void create(Integer id, Integer formId, Integer serviceLevelId, String customerEmail, String textTrademark, List terms) {
		String SQL = "INSERT INTO FormSubmission (id, formId, serviceLevelId, customerEmail, textTrademark) VALUES (:id, :formId, :serviceLevelId, :customerEmail, :textTrademark)";
		Map namedParameters = new HashMap();
		namedParameters.put("id", id);
		namedParameters.put("formId", formId);
		namedParameters.put("serviceLevelId", serviceLevelId);
		namedParameters.put("customerEmail", customerEmail);
		namedParameters.put("textTrademark", textTrademark);
		
		SQL =  "INSERT INTO FormSubmissionTerm (formSubmissionId, termId) VALUES (:formSubmissionId, :termId)";
		for (Term term : terms){
			namedParameters.put("formSubmissionId", id);
			namedParameters.put("termId", term.getId());
		}		
		
	}
	 */
	@Override
	public FormSubmission getFormSubmission(Integer id) {
		String SQL = "SELECT * FROM FormSubmission WHERE id= :id";
		SqlParameterSource namedParametersource = new MapSqlParameterSource("id", Integer.valueOf(id));
		FormSubmission formSubmission = (FormSubmission) namedParameterJdbcTemplate.queryForObject(SQL, namedParametersource, new FormSubmissionMapper());
		SQL = "SELECT t.id, t.name, t.termClassId"
			+" FROM Term t JOIN FormSubmissionTerm ft ON ( t.id = ft.termId AND ft.formSubmissionId  = :id) "
			+" ORDER BY t.termClassId, t.name; ";
		formSubmission.setTerms((ArrayList<Term>) namedParameterJdbcTemplate.query(SQL, namedParametersource, new TermMapper()));
		return formSubmission;
	}

	@Override
	public List<FormSubmission> listFormSubmission() {
		String SQL = "SELECT * FROM FormSubmission";
		List<FormSubmission> formSubmissions = (ArrayList<FormSubmission>) namedParameterJdbcTemplate.query(SQL, new FormSubmissionMapper());
		
		for (FormSubmission formSubmission: formSubmissions){
			int id = (int)formSubmission.getId();
			SqlParameterSource namedParametersource = new MapSqlParameterSource("id", Integer.valueOf(id));
			SQL = "SELECT t.id, t.name, t.termClassId"
				+" FROM Term t JOIN FormSubmissionTerm ft ON ( t.id = ft.termId AND ft.formSubmissionId  = :id) "
				+" ORDER BY t.termClassId, t.name; ";
			formSubmission.setTerms((ArrayList<Term>) namedParameterJdbcTemplate.query(SQL, namedParametersource, new TermMapper()));
		}
		return formSubmissions;
	}

	@Override
	public void delete(Integer id) {
		String SQL = "DELETE FROM FormSubmission WHERE id = :id";
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", Integer.valueOf(id));
		namedParameterJdbcTemplate.update(SQL, namedParameters);
		System.out.println("Deleted FormSubmission with ID = " + id );		
	}

	@Override
	public void update(Integer id, Integer formId, Integer serviceLevelId, String customerEmail, String textTrademark) {
		String SQL = "UPDATE FormSubmission SET formId = :formId, serviceLevelId = :serviceLevelId, customerEmail = :customerEmail, textTrademark = :textTrademark, WHERE id = :id";
		SqlParameterSource namedParameters = new MapSqlParameterSource();
		((MapSqlParameterSource) namedParameters).addValue("id", id);
		((MapSqlParameterSource) namedParameters).addValue("formId", formId);
		((MapSqlParameterSource) namedParameters).addValue("serviceLevelId", serviceLevelId);
		((MapSqlParameterSource) namedParameters).addValue("customerEmail", customerEmail);
		((MapSqlParameterSource) namedParameters).addValue("textTrademark", textTrademark);
		
		namedParameterJdbcTemplate.update(SQL, namedParameters);
		System.out.println("Updated Form with ID = " + id + " FormId = " + formId);
	}

	@Override
	public void createFormSubmission(FormSubmission formsubmission) {
		
		String SQL = "INSERT INTO FormSubmission (id, formId, serviceLevelId, customerEmail, textTrademark) VALUES (:id, :formId, :serviceLevelId, :customerEmail, :textTrademark)";
		Map namedParameters = new HashMap();
		namedParameters.put("id", formsubmission.getId());
		namedParameters.put("formId", formsubmission.getFormId());
		namedParameters.put("serviceLevelId", formsubmission.getServiceLevelId());
		namedParameters.put("customerEmail", formsubmission.getCustomerEmail());
		namedParameters.put("textTrademark", formsubmission.getTextTrademark());
		namedParameterJdbcTemplate.update(SQL, namedParameters);
	}

	@Override
	public void createFormSubmissionTerm(FormSubmission formsubmission) {
		// TODO Auto-generated method stub

		String SQL =  "INSERT INTO FormSubmissionTerm (formSubmissionId, termId) VALUES (:formSubmissionId, :termId)";
		
		for (Term term : formsubmission.getTerms()){
			Map namedParameters = new HashMap();
			namedParameters.put("formSubmissionId", formsubmission.getId());
			namedParameters.put("termId", term.getId());
			namedParameterJdbcTemplate.update(SQL, namedParameters);
		}		
		
		
	}
	
	@Override
	public void update(FormSubmission formsubmission) {
		
		String SQL = "UPDATE FormSubmission SET formId = :formId, serviceLevelId = :serviceLevelId, customerEmail = :customerEmail, textTrademark = :textTrademark WHERE id = :id";
		SqlParameterSource namedParameters = new MapSqlParameterSource();
		
		((MapSqlParameterSource) namedParameters).addValue("id", formsubmission.getId());
		((MapSqlParameterSource) namedParameters).addValue("formId", formsubmission.getFormId());
		((MapSqlParameterSource) namedParameters).addValue("serviceLevelId", formsubmission.getServiceLevelId());
		((MapSqlParameterSource) namedParameters).addValue("customerEmail", formsubmission.getCustomerEmail());
		((MapSqlParameterSource) namedParameters).addValue("textTrademark", formsubmission.getTextTrademark());
		
		namedParameterJdbcTemplate.update(SQL, namedParameters);
		System.out.println("Updated Form with ID = " + formsubmission.getId() + " FormId = " + formsubmission.getFormId());
		
	}
	
	@Override
	public void deleteFormSubmissionTerms(FormSubmission formsubmission) {
		String SQL = "DELETE FROM FormsubmissionTerm WHERE formSubmissionId = :formSubmissionId";
		SqlParameterSource namedParameters = new MapSqlParameterSource("formSubmissionId", formsubmission.getId());
		namedParameterJdbcTemplate.update(SQL, namedParameters);
				
		//update formsubmissionterms
		/*
		String SQL =  "UPDATE  FormSubmissionTerm SET termId = :termId, WHERE formSubmissionId = :formSubmissionId";
		
		for (Term term : formsubmission.getTerms()){
			SqlParameterSource namedParameters = new MapSqlParameterSource();
			((MapSqlParameterSource) namedParameters).addValue("formSubmissionId", formsubmission.getId());
			((MapSqlParameterSource) namedParameters).addValue("termId", term.getId());
			namedParameterJdbcTemplate.update(SQL, namedParameters);
		
		}*/
				
	}

}
