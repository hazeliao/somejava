package com.sp17.dao.impl;

import java.sql.SQLException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.sp17.dao.FormSubmissionHistoryDao;
import com.sp17.domain.FormSubmissionHistory;
import com.sp17.domain.Term;
import com.sp17.mapper.FormSubmissionHistoryMapper;
import com.sp17.mapper.TermMapper;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

@Component
public class FormSubmissionHistoryImpl implements FormSubmissionHistoryDao{
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		  this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	 }
	/*
	@Override
	public void create(Integer id, Integer dateAndTime, Integer empId, String formSubmissionid, String description, List terms) {
		String SQL = "INSERT INTO FormSubmissionHistory (id, dateAndTime, empId, formSubmissionid, description) VALUES (:id, :dateAndTime, :empId, :formSubmissionid, :description)";
		Map namedParameters = new HashMap();
		namedParameters.put("id", id);
		namedParameters.put("dateAndTime", dateAndTime);
		namedParameters.put("empId", empId);
		namedParameters.put("formSubmissionid", formSubmissionid);
		namedParameters.put("description", description);
		
		SQL =  "INSERT INTO FormSubmissionHistoryTerm (formSubmissionHistoryId, termId) VALUES (:formSubmissionHistoryId, :termId)";
		for (Term term : terms){
			namedParameters.put("formSubmissionHistoryId", id);
			namedParameters.put("termId", term.getId());
		}		
		
	}
	 */
	
	@Override
	public FormSubmissionHistory getFormSubmissionHistory(Integer id) {
		//String SQL = "SELECT * FROM FormSubmissionHistory WHERE id= :id";
		String SQL = "SELECT f.id, f.dateAndTime, f.empName, f.formSubmissionId, f.description, f.eventTypeId, e.contacted FROM FormSubmissionHistory f JOIN EventType e ON (e.id = f.eventTypeId) WHERE id = :id";
		SqlParameterSource namedParametersource = new MapSqlParameterSource("id", Integer.valueOf(id)); 
		FormSubmissionHistory formSubmissionHistory = (FormSubmissionHistory) namedParameterJdbcTemplate.queryForObject(SQL, namedParametersource, new FormSubmissionHistoryMapper());
		return formSubmissionHistory;
	}

	@Override
	public FormSubmissionHistory getLatestHistory(Integer id) {
		String SQL = "SELECT f.id, f.dateAndTime, f.empName, f.formSubmissionId, f.description, f.eventTypeId, e.contacted FROM FormSubmissionHistory f JOIN EventType e ON (e.id = f.eventTypeId) WHERE formSubmissionId = :id ORDER BY dateAndTime DESC LIMIT 1;";
		
		SqlParameterSource namedParametersource = new MapSqlParameterSource("id", Integer.valueOf(id)); 
		List<FormSubmissionHistory> formSubmissionHistorys = (List<FormSubmissionHistory>) namedParameterJdbcTemplate.query(SQL, namedParametersource, new FormSubmissionHistoryMapper());
		System.out.println("Size: " + formSubmissionHistorys.size());
		FormSubmissionHistory formSubmissionHistory = formSubmissionHistorys.get(0);
		
		return formSubmissionHistory;
		
	}
	@Override
	public List<FormSubmissionHistory> listFormSubmissionHistory() {
		String SQL = "SELECT f.id, f.dateAndTime, f.empName, f.formSubmissionId, f.description, f.eventTypeId, e.contacted FROM FormSubmissionHistory f JOIN EventType e ON (e.id = f.eventTypeId);";
		List<FormSubmissionHistory> formSubmissionHistorys = (ArrayList<FormSubmissionHistory>) namedParameterJdbcTemplate.query(SQL, new FormSubmissionHistoryMapper());

		//System.out.println(formSubmissionHistorys);
		return formSubmissionHistorys;
	}
	
	@Override
	public List<FormSubmissionHistory> listHistoryBySubmissionId(int id) {
		String SQL = "SELECT f.id, f.dateAndTime, f.empName, f.formSubmissionId, f.description, f.eventTypeId, e.contacted FROM FormSubmissionHistory f JOIN EventType e ON (e.id = f.eventTypeId) WHERE formSubmissionId = :id;";
		SqlParameterSource namedParametersource = new MapSqlParameterSource("id", Integer.valueOf(id)); 
		List<FormSubmissionHistory> formSubmissionHistorys = (ArrayList<FormSubmissionHistory>) namedParameterJdbcTemplate.query(SQL,namedParametersource, new FormSubmissionHistoryMapper());

		//System.out.println(formSubmissionHistorys);
		return formSubmissionHistorys;
	}

	@Override
	public void delete(Integer id) {
		String SQL = "DELETE FROM FormSubmissionHistory WHERE id = :id";
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", Integer.valueOf(id));
		namedParameterJdbcTemplate.update(SQL, namedParameters);
		System.out.println("Deleted FormSubmissionHistory with ID = " + id );		
	}

	@Override
	public void update(long id, Date dateAndTime,  String empName, long formSubmissionId, String description, long eventTypeId) {
		String SQL = "UPDATE Form SET dateAndTime = :dateAndTime, empId = :empId, formSubmissionid = :formSubmissionid, description = :description, WHERE id = :id";
		SqlParameterSource namedParameters = new MapSqlParameterSource();
		((MapSqlParameterSource) namedParameters).addValue("id", id);
		((MapSqlParameterSource) namedParameters).addValue("dateAndTime", dateAndTime);
		((MapSqlParameterSource) namedParameters).addValue("empName", empName);
		((MapSqlParameterSource) namedParameters).addValue("formSubmissionId", formSubmissionId);
		((MapSqlParameterSource) namedParameters).addValue("description", description);
		((MapSqlParameterSource) namedParameters).addValue("eventTypeId", eventTypeId);
		
		namedParameterJdbcTemplate.update(SQL, namedParameters);
		System.out.println("Updated Form with ID = " + id + " Id = " + id);
	}

	@Override
	public void createFormSubmissionHistory(FormSubmissionHistory formsubmissionhistory) {
		
		String SQL = "INSERT INTO FormSubmissionHistory (id, dateAndTime, empName, formSubmissionid, description, eventTypeId) VALUES (:id, :dateAndTime, :empName, :formSubmissionid, :description, :eventTypeId)";
		Map namedParameters = new HashMap();
		namedParameters.put("id", formsubmissionhistory.getId());
		namedParameters.put("dateAndTime", formsubmissionhistory.getDateAndTime());
		namedParameters.put("empName", formsubmissionhistory.getEmpName());
		namedParameters.put("formSubmissionid", formsubmissionhistory.getFormSubmissionId());
		namedParameters.put("description", formsubmissionhistory.getDescription());
		namedParameters.put("eventTypeId", formsubmissionhistory.getEventId());
		namedParameterJdbcTemplate.update(SQL, namedParameters);
	}


	

}