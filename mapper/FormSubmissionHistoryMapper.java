package com.sp17.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sp17.domain.FormSubmissionHistory;

public class FormSubmissionHistoryMapper implements RowMapper<FormSubmissionHistory> {
	
	public FormSubmissionHistory mapRow(ResultSet rs, int rowNum) throws SQLException{
		FormSubmissionHistory formSubmissionHistory = new FormSubmissionHistory();
		formSubmissionHistory.setId(rs.getLong("id"));
		formSubmissionHistory.setDateAndTime(rs.getString("dateAndTime"));
		formSubmissionHistory.setEmpName(rs.getString("empName"));
		formSubmissionHistory.setFormSubmissionId(rs.getLong("formSubmissionId"));
		formSubmissionHistory.setDescription(rs.getString("description"));
		formSubmissionHistory.setEventId(rs.getInt("eventTypeId"));
		formSubmissionHistory.setContacted(rs.getInt("contacted"));
		return formSubmissionHistory;
	}
}
