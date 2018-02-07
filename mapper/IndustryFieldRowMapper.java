package com.sp17.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sp17.domain.IndustryField;

public class IndustryFieldRowMapper implements RowMapper<IndustryField>{
	public IndustryField mapRow(ResultSet rs, int rowNum) throws SQLException{
		
		IndustryField industryField= new IndustryField();
		
		industryField.setId(rs.getInt("id"));
		industryField.setName(rs.getString("name"));
		industryField.setPriority(rs.getInt("priority"));
		industryField.setIconFilePath(rs.getString("iconFilePath"));
		
		return industryField;
	}
}
