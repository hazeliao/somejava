package com.sp17.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.sp17.dao.FormDao;
import com.sp17.domain.Form;
import com.sp17.domain.ServiceLevel;
import com.sp17.domain.Term;
import com.sp17.mapper.FormMapper;
import com.sp17.mapper.ServiceLevelMapper;
import com.sp17.mapper.TermMapper;

@Component
public class FormDaoImpl implements FormDao{
	@Autowired	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		  this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	 }
	
	
	@Override
	public void createForm(Form form) {
	      String SQL = "INSERT INTO Form (id, name) VALUES (:id, :name)";
	      Map namedParameters = new HashMap();  
	      namedParameters.put("id", form.getId());
	      namedParameters.put("name", form.getName()); 
	      namedParameterJdbcTemplate.update(SQL, namedParameters);
	      System.out.println("Created Form Id = " + form.getId() + " Name = " + form.getName());
	}
	
	

	@Override
	public void createFormTerm(Form form) {
		String SQL = "INSERT INTO FormTerm (formId, termId, relevance) VALUES (:formId, :termId, :relevance)";
		
		for (Term term : form.getTerms1()){
			Map namedParameters = new HashMap();
			namedParameters.put("formId", form.getId());
			namedParameters.put("termId", term.getId());
			namedParameters.put("relevance", 1);
			namedParameterJdbcTemplate.update(SQL, namedParameters);			
		}
		
		for (Term term : form.getTerms2()){
			Map namedParameters = new HashMap();
			namedParameters.put("formId", form.getId());
			namedParameters.put("termId", term.getId());
			namedParameters.put("relevance", 2);
			namedParameterJdbcTemplate.update(SQL, namedParameters);			
		}
	}
	
	@Override
	public void createIndustryFieldForm(Form form, HashMap<Integer, Integer> priorityMap) {
		String SQL = "INSERT INTO IndustryFieldForm (industryFieldId, formId, priority) VALUES (:industryFieldId, :formId, :priority)";
				 
		for (Map.Entry<Integer, Integer> entry : priorityMap.entrySet()){
			Map namedParameters = new HashMap();
			namedParameters.put("industryFieldId", entry.getKey());
			namedParameters.put("formId", form.getId());
			namedParameters.put("priority", entry.getValue());
			namedParameterJdbcTemplate.update(SQL, namedParameters);
		}
		
	}

	@Override
	public Form getForm(Integer id){
		
		SqlParameterSource namedParametersource = new MapSqlParameterSource("id", Integer.valueOf(id));
		String SQL = "SELECT * FROM Form WHERE id= :id";
		Form form = (Form) namedParameterJdbcTemplate.queryForObject(SQL, namedParametersource, new FormMapper());
		
		/* Tested SQL: 
		SELECT t.id, t.name, t.termClassId 
			FROM Term t
				JOIN FormTerm ft ON ( t.id = ft.termId AND ft.formId = 101)
			WHERE ft.relevance = 1
			ORDER BY t.name;	
		 */
		
		SQL="SELECT t.id, t.name, t.termClassId "
			+" FROM Term t JOIN FormTerm ft ON ( t.id = ft.termId AND ft.formId = :id) "
			+" WHERE ft.relevance = 1 "
			+" ORDER BY t.termClassId, t.name; ";	
		form.setTerms1((ArrayList<Term>) namedParameterJdbcTemplate.query(SQL, namedParametersource, new TermMapper()) );
		
		SQL="SELECT t.id, t.name, t.termClassId "
			+" FROM Term t JOIN FormTerm ft ON ( t.id = ft.termId AND ft.formId = :id) "
			+" WHERE ft.relevance = 2 "
			+" ORDER BY t.termClassId,  t.name; ";	
		form.setTerms2((ArrayList<Term>) namedParameterJdbcTemplate.query(SQL, namedParametersource, new TermMapper()) );
		
		SQL="SELECT * FROM ServiceLevel;";
		form.setServiceLevels((ArrayList<ServiceLevel>) namedParameterJdbcTemplate.query(SQL, new ServiceLevelMapper()));
				return form;
	}

	@Override
	public List<Form> listForms() {
		String SQL = "SELECT * FROM Form";
		List<Form> forms= (ArrayList<Form>) namedParameterJdbcTemplate.query(SQL, new FormMapper());
		return forms;
	}

	@Override
	public void delete(Integer id) {		
		String SQL = "DELETE FROM Form WHERE id = :id";
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", Integer.valueOf(id));
		namedParameterJdbcTemplate.update(SQL, namedParameters);
		System.out.println("Deleted Form with ID = " + id );
	}

	@Override
	public void update(Integer id, String name) {		
		String SQL = "UPDATE Form SET name = :name WHERE id = :id";
		SqlParameterSource namedParameters = new MapSqlParameterSource();
		((MapSqlParameterSource) namedParameters).addValue("id", id);
		((MapSqlParameterSource) namedParameters).addValue("name", name);
		namedParameterJdbcTemplate.update(SQL, namedParameters);
		System.out.println("Updated Form with ID = " + id + " Name = " + name);
	}


}
