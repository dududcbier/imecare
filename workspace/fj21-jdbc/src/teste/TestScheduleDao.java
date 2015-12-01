package teste;

import static org.junit.Assert.assertEquals;

import org.junit.*;
import bd.ScheduleDao;
import bd.DoctorDao;
import bd.PersonDao;
import bd.PatientDao;
import modelo.Schedule;
import modelo.Doctor;
import modelo.Patient;
import java.sql.*;
import java.util.*;

public class TestScheduleDao {

	@Test(expected=RuntimeException.class)
	public void insertPatientEmptyCPF() {

		Schedule sched = new Schedule();
		sched.setDoctorCpf("12345678901");
		sched.setPatientCpf("");
		sched.setDateTime(Calendar.getInstance());
		sched.setType("refazer");
		sched.setComment("Simpático");

		ScheduleDao schedDao = new ScheduleDao();
		schedDao.addSchedule(sched);
	}

	@Test(expected=RuntimeException.class)
	public void insertDoctorEmptyCPF() {

		Schedule sched = new Schedule();
		sched.setDoctorCpf(null);
		sched.setPatientCpf("10987654321");
		sched.setDateTime(Calendar.getInstance());
		sched.setType("refazer");
		sched.setComment("Simpático");

		ScheduleDao schedDao = new ScheduleDao();
		schedDao.addSchedule(sched);
	}

	@Test
	public void insertValidSchedule() {

		Schedule sched = new Schedule();
		sched.setDoctorCpf("12345678901");
		sched.setPatientCpf("10987654321");
		sched.setDateTime(Calendar.getInstance());
		sched.setType("refazer");
		sched.setComment("Simpático");

		ScheduleDao schedDao = new ScheduleDao();
		schedDao.addSchedule(sched);
	}

	@Test(expected=RuntimeException.class) 
	public void insertDuplicatedSchedule(){

		Schedule sched = new Schedule();
		sched.setDoctorCpf("12345678901");
		sched.setPatientCpf("10987654321");
		sched.setDateTime(Calendar.getInstance());
		sched.setType("refazer");
		sched.setComment("Simpático");

		ScheduleDao schedDao = new ScheduleDao();
		schedDao.addSchedule(sched);
		schedDao.addSchedule(sched);
	}

	@Test(expected=RuntimeException.class) 
	public void insertNullDate(){

		Schedule sched = new Schedule();
		sched.setDoctorCpf("12345678901");
		sched.setPatientCpf("10987654321");
		sched.setDateTime(null);
		sched.setType("refazer");
		sched.setComment("Simpático");

		ScheduleDao schedDao = new ScheduleDao();
		schedDao.addSchedule(sched);
	}

	@Test
	public void editValidSchedule(){

		Schedule sched = new Schedule();
		sched.setDoctorCpf("12345678901");
		sched.setPatientCpf("10987654321");
		sched.setDateTime(Calendar.getInstance());
		sched.setType("refazer");
		sched.setComment("Simpático");

		ScheduleDao schedDao = new ScheduleDao();
		schedDao.addSchedule(sched);

		sched.setComment("Diferente");
		
		schedDao.updateSchedule(sched);

	}

	// Esse teste (abaixo) deveria dar Runtime Error quando a consulta não
	// existe, mas como usamos o update na hora de alterar a consulta,
	// se ela não existir ele acaba criando um novo.

	@Test(expected=RuntimeException.class) 
	public void editInvalidSchedule(){

		Schedule sched = new Schedule();
		sched.setDoctorCpf("90328432902");
		sched.setPatientCpf("8947238232");
		sched.setDateTime(Calendar.getInstance());
		sched.setType("refazer");
		sched.setComment("Simpático");

		ScheduleDao schedDao = new ScheduleDao();
		schedDao.updateSchedule(sched);
	}

	@Test
	public void deleteValidSchedule(){

		Schedule sched = new Schedule();
		sched.setDoctorCpf("12345678901");
		sched.setPatientCpf("10987654321");
		sched.setDateTime(Calendar.getInstance());
		sched.setType("refazer");
		sched.setComment("Simpático");

		ScheduleDao schedDao = new ScheduleDao();
		schedDao.addSchedule(sched);
		schedDao.removeSchedule(sched);

	}

	// Esse teste (abaixo) também deveria levantar uma exceção, 
	// para pelo menos avisar que a consulta que estavamos
	// tentando deletar não existe...

	@Test(expected=RuntimeException.class)
	public void deleteInvalidSchedule(){

		Schedule sched = new Schedule();
		sched.setDoctorCpf("00000000000");
		sched.setPatientCpf("10987654321");
		sched.setDateTime(Calendar.getInstance());
		sched.setType("refazer");
		sched.setComment("Simpático");
		
		ScheduleDao schedDao = new ScheduleDao();
		schedDao.removeSchedule(sched);
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
