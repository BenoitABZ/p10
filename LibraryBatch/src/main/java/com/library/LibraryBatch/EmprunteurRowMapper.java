package com.library.LibraryBatch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.library.LibraryBatch.bean.EmprunteurBean;

public class EmprunteurRowMapper implements RowMapper<EmprunteurBean> {
	
	@Override
	  public EmprunteurBean mapRow(ResultSet rs, int rowNum) throws SQLException {
	  
		EmprunteurBean emprunteurBean = new EmprunteurBean();
	   
	   emprunteurBean.setMail(rs.getString("adresse_mail"));
	 
	   
	   return emprunteurBean;

}
}