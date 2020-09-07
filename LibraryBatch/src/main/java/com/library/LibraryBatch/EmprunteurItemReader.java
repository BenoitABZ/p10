package com.library.LibraryBatch;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.support.ListItemReader;

import com.library.LibraryBatch.bean.EmprunteurBean;

public class EmprunteurItemReader {
/*
	private static final String QUERY_FIND_EMPRUNTEUR =
   "SELECT emprunteur.adresse_mail "
   + "FROM emprunteur "
   + "JOIN emprunt "
   + "WHERE emprunteur.id_emprunteur=emprunt.id_emprunteur "
   + "AND DATEDIFF(dd, emprunt.date_emprunt, current_date) > 60 ;";
 
    
         public ItemReader<EmprunteurBean> read(DataSource dataSource) {
        JdbcCursorItemReader<EmprunteurBean> databaseReader = new JdbcCursorItemReader<>();
 
        databaseReader.setDataSource(dataSource);
        databaseReader.setSql(QUERY_FIND_EMPRUNTEUR);
        databaseReader.setRowMapper(new EmprunteurRowMapper());
       
 
        return databaseReader;
     }
     
     */
	
    public ItemReader<EmprunteurBean> read(EmprunteurProxy emprunteurProxy) {
    	
     
    	List<EmprunteurBean> emprunteursList =  emprunteurProxy.getEmprunteursRetardataires();
       
    	ListItemReader<EmprunteurBean> emprunteurs = new ListItemReader<EmprunteurBean>(emprunteursList);
 
        return emprunteurs;
     }
	
	
}


