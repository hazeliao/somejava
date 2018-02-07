package com.sp17.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sp17.domain.Term;
import com.sp17.domain.TermClass;

public class TermMapper implements RowMapper<Term>{
	
	public Term mapRow(ResultSet rs, int rowNum) throws SQLException{
		Term term = new Term();
		term.setId(rs.getLong("id"));
		term.setName(rs.getString("name"));
		term.setTermClass( new TermClass(rs.getLong("termClassId")));
		return term;
	}

}
