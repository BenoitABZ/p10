package com.library.LibraryBatch.reader;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.support.ListItemReader;

import com.library.LibraryBatch.bean.EmprunteurBean;
import com.library.LibraryBatch.proxy.EmprunteurProxy;

public class EmprunteurNotifyItemReader {

	public ItemReader<EmprunteurBean> read(EmprunteurProxy emprunteurProxy) {

		List<EmprunteurBean> emprunteursList = emprunteurProxy.notifyEmprunteur();

		ListItemReader<EmprunteurBean> emprunteurs = new ListItemReader<EmprunteurBean>(emprunteursList);

		return emprunteurs;
	}

}
