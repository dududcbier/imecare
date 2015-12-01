package teste;

import static org.junit.Assert.assertEquals;

import org.junit.*;
import bd.PatientDao;
import bd.RecordsDao;
import modelo.Patient;
import modelo.Prescreve;
import modelo.Records;
import java.sql.*;
import java.util.*;

public class TestPatient {

	@Test(expected=RuntimeException.class)
	public void insertPatientEmptyCPF() {

		Patient patient = new Patient();
		patient.setBloodType("B+");
		patient.setCpf("");
		
		PatientDao patDao = new PatientDao();	
		patDao.addPatient(patient);
	}


	@Test
	public void insertValidPatient() {

		Patient patient = new Patient();
		patient.setCpf("10987654321");
		patient.setName("Patient");
		patient.setEmail("patient@patient.com.br");
		patient.setAddress("Rua dos patients, 123");
		patient.setBirthDate(Calendar.getInstance());
		patient.setRg("987654321");
		patient.setBloodType("O+");

		PatientDao patDao = new PatientDao();	
		patDao.addPatient(patient);
	}


	@Test(expected=RuntimeException.class) 
	public void insertDuplicatedCpf(){

		Patient patient = new Patient();
		
		patient.setBloodType("B+");
		patient.setCpf("10987654321");

		PatientDao patDao = new PatientDao();
		patDao.addPatient(patient);
	}


	@Test
	public void editValidPatient(){

		Patient patient = new Patient ();
		patient.setBloodType("A+");
		patient.setCpf("10987654321");
		
		PatientDao patDao = new PatientDao();	
		patDao.addPatient(patient);
		
		patDao.updatePatient(patient);

	}


    //Deveria dar RuntimeException, mas o update acaba criando se não existir

	@Test(expected=RuntimeException.class) 
	public void editInvalidPatient(){

		Patient patient = new Patient ();

		patient.setBloodType("AB+");
		patient.setCpf("10987654399");

	   	PatientDao patDao = new PatientDao();	
		patDao.addPatient(patient);
		
		patDao.updatePatient(patient);

	}

	@Test
	public void deleteValidPatient(){

		Patient patient = new Patient ();
		patient.setCpf("10987654321");
		patient.setBloodType("O+");

		PatientDao patDao = new PatientDao();	
		patDao.removePatient(patient);


	}

	// Esse teste deveria levantar uma exceção já que o user não existe

	@Test(expected=RuntimeException.class)
	public void deleteInvalidPatient(){


        Patient patient = new Patient ();
		patient.setCpf("00000000001");
		patient.setBloodType("O+");
		
 		PatientDao patDao = new PatientDao();	
		patDao.removePatient(patient);
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PatientDao patientDao = new PatientDao();
		Patient patient = new Patient();
		patient.setCpf("10987654321");
		patient.setName("Patient");
		patient.setEmail("patient@patient.com.br");
		patient.setAddress("Rua dos patients, 123");
		patient.setBirthDate(Calendar.getInstance());
		patient.setRg("987654321");
		patient.setBloodType("O+");

		try {
			patientDao.addPatient(patient);
		}
		catch (RuntimeException e){
			System.out.println("Não consegui Inserir");
			try {
				patientDao.removePatient(patient);
			}
			catch (RuntimeException f){
				System.out.println("Não consegui remover");
			}
			System.out.println("Tentando de novo...");
			patientDao.addPatient(patient);
		}
	}

	@AfterClass
	public static void setUpAfterClass() throws Exception {
		PatientDao patientDao = new PatientDao();
		Patient patient = new Patient();
		patient.setCpf("10987654321");
		patientDao.removePatient(patient);
	}

} 
