package teste;

import static org.junit.Assert.assertEquals;

import org.junit.*;
import bd.SymptonDao;
import bd.RecordsDao;
import modelo.Sympton;
import modelo.Prescreve;
import modelo.Records;
import java.sql.*;
import java.util.*;

public class TestSympton {

	private SymptonDao symDao = new SymptonDao();

	@Test(expected=RuntimeException.class)
	public void insertSymptonWithouDescription() {

		Sympton sympton = new Sympton();
		sympton.setName("Gripe");
		sympton.setDescription("");
		
		symDao.addSympton(sympton);
	}


	@Test
	public void insertValidSympton() {

		Sympton sympton = new Sympton ();

		sympton.setName("Gripe");
		sympton.setDescription("Vermelhidao");
		
		symDao.addSympton(sympton);
	}


	@Test(expected=RuntimeException.class) 
	public void insertDuplicatedName(){

	        Sympton sympton = new Sympton ();

		sympton.setName("Gripe");
		sympton.setDescription("De novo");

		symDao.addSympton(sympton);
		symDao.addSympton(sympton);

	}


	@Test
	public void editValidSympton(){

		Sympton sympton = new Sympton ();

		sympton.setName("Gripe");
		sympton.setDescription("Febre, dor, coriza");     
		
		symDao.addSympton(sympton);
		sympton.setDescription("Febre, dor de cabeca, coriza");
		symDao.updateSympton(sympton);

	}


    //Deveria dar RuntimeException, mas o update acaba criando se não existir

	@Test(expected=RuntimeException.class) 
	public void editInvalidSympton(){

		Sympton sympton = new Sympton ();

		sympton.setName("Nao existe");
		sympton.setDescription("Descricao nova");

		symDao.updateSympton(sympton);

	}

	@Test
	public void deleteValidSympton(){

		Sympton sympton = new Sympton ();
		sympton.setName("Gripe");
		sympton.setDescription("Coriza dor de cabeça febre");

		symDao.removeSympton(sympton);


	}

	// Esse teste deveria levantar uma exceção já que o user não existe

	@Test(expected=RuntimeException.class)
	public void deleteInvalidSympton(){


        Sympton sympton = new Sympton ();
		sympton.setName("Nao existe");
		sympton.setDescription("Alguma descricao");
		
		symDao.removeSympton(sympton);
	}

	@After
	public void tearDown() throws Exception {
		Sympton sympton = new Sympton();
		sympton.setName("Gripe");
		symDao.removeSympton(sympton);
	}

} 
