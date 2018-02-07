package com.sp17.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sp17.domain.Form;

public class FormMapper implements RowMapper<Form>{
	
	public Form mapRow(ResultSet rs, int rowNum) throws SQLException{
		Form form= new Form();
		form.setId(rs.getLong("id"));
		form.setName(rs.getString("name"));
		return form;
	}

}
