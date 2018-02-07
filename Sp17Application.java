package com.sp17;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.sp17.dao.impl.FormDaoImpl;
import com.sp17.domain.Form;
import com.sp17.domain.ServiceLevel;
import com.sp17.dao.impl.IndustryFieldImpl;
import com.sp17.dao.impl.FormSubmissionDaoImpl;
import com.sp17.dao.impl.FormSubmissionHistoryImpl;
import com.sp17.dao.impl.TermDaoImpl;
import com.sp17.Sp17Application;

@SpringBootApplication
public class Sp17Application {
	
	private static final Logger log = LoggerFactory.getLogger(Sp17Application.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Sp17Application.class, args);
	}
	
	public static FormDaoImpl formDatabase = new FormDaoImpl();
	
	public static IndustryFieldImpl industryFieldDatabase = new IndustryFieldImpl();
	
	public static FormSubmissionDaoImpl formSubmissionDatabase = new FormSubmissionDaoImpl();
	
	public static FormSubmissionHistoryImpl formSubmissionHistoryDatabase = new FormSubmissionHistoryImpl();
	
	public static TermDaoImpl termDatabase = new TermDaoImpl();
	
	
	@Bean
	public CommandLineRunner demo(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
		return(args) -> {
			log.info("save a couple of cps");
						
			// database = new FormDaoImpl();
			formDatabase.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate);
			industryFieldDatabase.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate);
			formSubmissionDatabase.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate);
			formSubmissionHistoryDatabase.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate);
			termDatabase.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate);
			
			//test data in console
			Form form = formDatabase.getForm(101);
			List<ServiceLevel> sl = form.getServiceLevels();			
			System.out.println("forms: " + form.getTerms1().toString());
			System.out.println("servicelevels: " + sl.toString());
			System.out.println("servicelevels.size(): " + sl.size());
			
			//TEST DB
			//database.create(1, "form1");

					

		};
	}
}
