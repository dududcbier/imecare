package teste;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import bd.DoctorDao;
import modelo.Doctor;
import java.sql.*;
import java.util.*;

public class TestDoctor {

	@Test(expected=RuntimeException.class)
	public void insertDoctorEmptyCPF() {

		Doctor doc = new Doctor();
		doc.setCrm(12);
		doc.setCpf("");
		
		DoctorDao docDao = new DoctorDao();
		docDao.addDoctor(doc);
	}


	@Test
	public void insertValidDoctor() {

		Doctor doc = new Doctor();
		doc.setCrm(13);
		doc.setCpf("10987654321");
		
		DoctorDao docDao = new DoctorDao();
		docDao.addDoctor(doc);
	}


	@Test(expected=RuntimeException.class) 
	public void insertDuplicatedCpf(){

		Doctor doc = new Doctor();
		
		doc.setCrm(12);
		doc.setCpf("10987654321");

		DoctorDao docDao = new DoctorDao();
		docDao.addDoctor(doc);

	}


	@Test
	public void editValidDoctor(){

		Doctor doc = new Doctor ();
		doc.setCrm(14);
		doc.setCpf("10987654321");
		
		DoctorDao docDao = new DoctorDao();
		docDao.addDoctor(doc);
		
		docDao.updateDoctor(doc);

	}


    //Deveria dar RuntimeException, mas o update acaba criando se não existir

	@Test(expected=RuntimeException.class)
	public void editInvalidDoctor(){

		Doctor doc = new Doctor ();

		doc.setCrm(16);
		doc.setCpf("10987654399");

		DoctorDao docDao = new DoctorDao();
	    docDao.updateDoctor(doc);

	}

	@Test
	public void deleteValidDoctor(){

		Doctor doc = new Doctor ();
		doc.setCpf("10987654321");
		doc.setCrm(12);

		DoctorDao docDao = new DoctorDao();
	    docDao.removeDoctor(doc);

	}

	// Esse teste deveria levantar uma exceção já que o user não existe

	@Test(expected=RuntimeException.class)
	public void deleteInvalidDoctor(){


        Doctor doc = new Doctor ();
		doc.setCpf("00000000001");
		doc.setCrm(15);
		
 		DoctorDao docDao = new DoctorDao();
	    docDao.removeDoctor(doc);

	}

} 
