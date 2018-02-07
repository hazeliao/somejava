package com.sp17.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sp17.domain.ServiceLevel;

public class ServiceLevelMapper implements RowMapper<ServiceLevel>{
	
	public ServiceLevel mapRow(ResultSet rs, int rowNum) throws SQLException{
		ServiceLevel serviceLevel = new ServiceLevel();
		serviceLevel.setId(rs.getLong("id"));
		serviceLevel.setName(rs.getString("name"));
		serviceLevel.setPrice(rs.getDouble("price"));
		return serviceLevel;
	}

}
