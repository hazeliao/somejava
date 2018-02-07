package com.sp17.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sp17.domain.FormSubmission;

public class FormSubmissionMapper implements RowMapper<FormSubmission> {
	
	public FormSubmission mapRow(ResultSet rs, int rowNum) throws SQLException{
		FormSubmission formSubmission = new FormSubmission();
		formSubmission.setId(rs.getLong("id"));
		formSubmission.setFormId(rs.getLong("formId"));
		formSubmission.setServiceLevelId(rs.getLong("serviceLevelId"));
		formSubmission.setCustomerEmail(rs.getString("customerEmail"));
		formSubmission.setTextTrademark(rs.getString("textTrademark"));
		return formSubmission;

	}
}
