package teste;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import bd.PatientDao;
import bd.RecordsDao;
import modelo.Patient;
import modelo.Prescreve;
import modelo.Records;
import java.sql.*;
import java.util.*;

public class TestPatient {

	public void insertPatient(Patient patient) {

		PatientDao patDao = new PatientDao();
		
		try {
			patientDao.addPatient(patient);
		}

		catch(SQLException e){
			return -1;
		}
		return 0;
	}


	private int editPatient(Patient patient){
		PatientDao patDao = new PatientDao();

		try {
			patDao.updatePatient(patient);
		}

		catch(SQLException e){
			return -1;
		}
		return 0;
	}

	private int deletePatient(Patient patient){
		PatientDao patDao = new PatientDao();

		try {
			patDao.removePatient(patient);
		}

		catch(SQLException e){
			return -1;
		}
		return 0;
	}

	@Test(expected=RuntimeException.class)
	public void insertPatientEmptyCPF() {

		Patient patient = new Patient();
		patient.setBloodType("B+");
		patient.setCpf("");
		
		insertPatient(patient);
	}


	@Test
	public void insertValidPatient() {

		Patient patient = new Patient();
		patient.setBloodType("A+");
		patient.setCpf("10987654321");
		
		assertEquals("Must be able to insert", 0, insertPatient(patient));
	}


	@Test(expected=RuntimeException.class) 
	public void insertDuplicatedCpf(){

		Patient patient = new Patient();
		
		patient.setBloodType("B+");
		patient.setCpf("10987654321");

		assertEquals("Must not be able to insert", -1, insertPatient(patient));

	}


	@Test
	public void editValidPatient(){

		Patient patient = new Patient ();
		patient.setBloodType("A+");
		patient.setCpf("10987654321");
		
		insertPatient(patient);
		
		editPatient(patient);

	}


    //Deveria dar RuntimeException, mas o update acaba criando se não existir

	@Test(expected=RuntimeException.class) 
	public void editInvalidPatient(){

		Patient patient = new Patient ();

		patient.setBloodType("AB+");
		patient.setCpf("10987654399");

	    editPatient(patient);

	}

	@Test
	public void deleteValidPatient(){

		Patient patient = new Patient ();
		patient.setPatientCpf("10987654321");
		patient.setBloodType("O+");

		deletePatient(patient);
		assertEquals("Must be able to delete", 0, deletePatient(patient));


	}

	// Esse teste deveria levantar uma exceção já que o user não existe

	@Test(expected=RuntimeException.class)
	public void deleteInvalidPatient(){


        Patient patient = new Patient ();
		patient.setPatientCpf("00000000001");
		patient.setBloodType("O+");
		
 		deletePatient(patient);
 		assertEquals("Must not be able to delete", -1, deletePatient(patient));

	}

} 
