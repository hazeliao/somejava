package com.sp17.dao.impl;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.sp17.dao.TermDao;
import com.sp17.domain.Term;
import com.sp17.mapper.TermMapper;

@Component
public class TermDaoImpl implements TermDao {
	@Autowired	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	 }

	@Override
	public void createTerm(Integer id, String name) {
		String SQL = "INSERT INTO Term (id, name) VALUES (:id, :name)";
		Map namedParameters = new HashMap();  
		namedParameters.put("id", id);
		namedParameters.put("name", name); 
		namedParameterJdbcTemplate.update(SQL, namedParameters);

	}

	@Override
	public Term getTerm(Integer id) {
		SqlParameterSource namedParametersource = new MapSqlParameterSource("id", Integer.valueOf(id));

		String SQL = "SELECT * FROM Term WHERE id= :id";
		
		Term term = (Term) namedParameterJdbcTemplate.queryForObject(SQL, namedParametersource, new TermMapper());
		return term;
	}

	@Override
	public List<Term> listTerms() {
		String SQL = "SELECT * FROM Term";
		List<Term> terms = (ArrayList<Term>) namedParameterJdbcTemplate.query(SQL, new TermMapper());
		return terms;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Integer id, String name) {
		// TODO Auto-generated method stub
		
	}

}
