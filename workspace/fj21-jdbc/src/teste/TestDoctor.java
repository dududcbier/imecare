package teste;

import static org.junit.Assert.assertEquals;

import org.junit.*;
import bd.DoctorDao;
import modelo.Doctor;
import java.sql.*;
import java.util.*;

public class TestDoctor {

	@Test(expected=RuntimeException.class)
	public void insertDoctorEmptyCPF() {

		Doctor doc = new Doctor();
		doc.setName("Médico");
		doc.setEmail("medico@medico.com.br");
		doc.setAddress("Rua dos médicos, 123");
		doc.setBirthDate(Calendar.getInstance());
		doc.setRg("123456789");
		doc.setCpf(null);
		doc.setCrm(13);
		doc.setEspecialidade("Neurologista");
		
		DoctorDao docDao = new DoctorDao();
		docDao.addDoctor(doc);
	}


	@Test
	public void insertValidDoctor() {

		Doctor doc = new Doctor();
		doc.setName("Médico");
		doc.setEmail("medico@medico.com.br");
		doc.setAddress("Rua dos médicos, 123");
		doc.setBirthDate(Calendar.getInstance());
		doc.setRg("123456789");
		doc.setCpf("10987654321");
		doc.setCrm(13);
		doc.setEspecialidade("Neurologista");
		
		DoctorDao docDao = new DoctorDao();
		docDao.addDoctor(doc);
	}


	@Test(expected=RuntimeException.class) 
	public void insertDuplicatedCpf(){

		Doctor doc = new Doctor();
		
		doc.setName("Médico");
		doc.setEmail("medico@medico.com.br");
		doc.setAddress("Rua dos médicos, 123");
		doc.setBirthDate(Calendar.getInstance());
		doc.setRg("123456789");
		doc.setCpf("10987654321");
		doc.setCrm(13);
		doc.setEspecialidade("Neurologista");

		DoctorDao docDao = new DoctorDao();
		docDao.addDoctor(doc);
		docDao.addDoctor(doc);

	}


	@Test
	public void editValidDoctor(){

		Doctor doc = new Doctor ();
		doc.setName("Médico");
		doc.setEmail("medico@medico.com.br");
		doc.setAddress("Rua dos médicos, 123");
		doc.setBirthDate(Calendar.getInstance());
		doc.setRg("123456789");
		doc.setCpf("10987654321");
		doc.setCrm(13);
		doc.setEspecialidade("Neurologista");
		
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

	// O teste abaixo era pra dar certo mas não da porque implementamos o getUser errado.
	// Deveriamos ter levado em conta que o email de uma pessoa está em uma tabela separa-
	// da do resto das informações.

	@Test
	public void getValidDoctor(){

		Doctor doc = new Doctor ();
		doc.setName("Médico");
		doc.setBirthDate(Calendar.getInstance());
		doc.setRg("123456789");
		doc.setCpf("10987654321");
		doc.setCrm(13);
		doc.setEspecialidade("Neurologista");
		
		DoctorDao docDao = new DoctorDao();
		docDao.addDoctor(doc);
		
		Doctor doc2 = docDao.getDoctor(doc.getCpf());
		assertEquals(doc, doc2);

	}

	@Test(expected=RuntimeException.class)
	public void getInvalidDoctor(){

		DoctorDao docDao = new DoctorDao();
		Doctor doc = docDao.getDoctor("78792749273");
	}

	@After
	public void tearDown() throws Exception {
		DoctorDao doctorDao = new DoctorDao();
		Doctor doctor = new Doctor();
		doctor.setCpf("10987654321");
		doctorDao.removeDoctor(doctor);
	}


} 
