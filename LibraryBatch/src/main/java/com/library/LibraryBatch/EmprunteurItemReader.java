package com.library.LibraryBatch;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.support.ListItemReader;

import com.library.LibraryBatch.bean.EmprunteurBean;

public class EmprunteurItemReader {

	public ItemReader<EmprunteurBean> read(EmprunteurProxy emprunteurProxy) {

		List<EmprunteurBean> emprunteursList = emprunteurProxy.getEmprunteursRetardataires();

		ListItemReader<EmprunteurBean> emprunteurs = new ListItemReader<EmprunteurBean>(emprunteursList);

		return emprunteurs;
	}
}
