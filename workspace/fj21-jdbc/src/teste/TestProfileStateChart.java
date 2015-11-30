package teste;

import static org.junit.Assert.assertEquals;

import org.junit.*;
import bd.ScheduleDao;
import bd.DiagnosedDiseaseDao;
import bd.PrescreveDao;
import bd.RecordsDao;
import bd.PersonDao;
import modelo.Schedule;
import modelo.DiagnosedDisease;
import modelo.Prescreve;
import modelo.Records;
import modelo.Person;
import java.sql.*;
import java.util.*;

public class TestProfileStateChart {

	@Test
	public void insertValidDiagnosedDisease() {

		Calendar data = Calendar.getInstance();

		Schedule sched = new Schedule();
		sched.setDoctorCpf("12345678901");
		sched.setPatientCpf("10987654321");
		sched.setDateTime(data);
		sched.setType("refazer");
		sched.setComment(" ");

		DiagnosedDisease diagDisease = new DiagnosedDisease();
		diagDisease.setCid("4224");
		diagDisease.setDoctorCpf("12345678901");
		diagDisease.setPatientCpf("10987654321");
		diagDisease.setDate(data);
		diagDisease.setTime(data);

		ScheduleDao schedDao = new ScheduleDao();
		try {
			schedDao.addSchedule(sched);
		}

		catch(SQLException e){
			throw new RuntimeException(e);
		}

		DiagnosedDiseaseDao diagnosedDiseaseDao = new DiagnosedDiseaseDao();
		diagnosedDiseaseDao.addDiagnosedDisease(diagDisease);

	}

	@Test(expected=RuntimeException.class)
	public void insertNoCorrespondingScheduleDiagnosedDisease() {

		Calendar data = Calendar.getInstance();

		DiagnosedDisease diagDisease = new DiagnosedDisease();
		diagDisease.setCid("4224");
		diagDisease.setDoctorCpf("12345678901");
		diagDisease.setPatientCpf("10987654321");
		diagDisease.setDate(data);
		diagDisease.setTime(data);

		DiagnosedDiseaseDao diagnosedDiseaseDao = new DiagnosedDiseaseDao();
		diagnosedDiseaseDao.addDiagnosedDisease(diagDisease);

	}

	@Test(expected=RuntimeException.class)
	public void insertNoCorrespondingDiseaseDiagnosedDisease() {

		Calendar data = Calendar.getInstance();

		DiagnosedDisease diagDisease = new DiagnosedDisease();
		diagDisease.setCid("0432");
		diagDisease.setDoctorCpf("12345678901");
		diagDisease.setPatientCpf("10987654321");
		diagDisease.setDate(data);
		diagDisease.setTime(data);

		DiagnosedDiseaseDao diagnosedDiseaseDao = new DiagnosedDiseaseDao();
		diagnosedDiseaseDao.addDiagnosedDisease(diagDisease);

	}

	@Test(expected=RuntimeException.class)
	public void insertNoCorrespondingPatientDiagnosedDisease() {

		Calendar data = Calendar.getInstance();

		DiagnosedDisease diagDisease = new DiagnosedDisease();
		diagDisease.setCid("4224");
		diagDisease.setDoctorCpf("12345678901");
		diagDisease.setPatientCpf("42352352321");
		diagDisease.setDate(data);
		diagDisease.setTime(data);

		DiagnosedDiseaseDao diagnosedDiseaseDao = new DiagnosedDiseaseDao();
		diagnosedDiseaseDao.addDiagnosedDisease(diagDisease);

	}

	@Test(expected=RuntimeException.class)
	public void insertNoCorrespondingDoctorDiagnosedDisease() {

		Calendar data = Calendar.getInstance();

		DiagnosedDisease diagDisease = new DiagnosedDisease();
		diagDisease.setCid("4224");
		diagDisease.setDoctorCpf("58932359201");
		diagDisease.setPatientCpf("10987654321");
		diagDisease.setDate(data);
		diagDisease.setTime(data);

		DiagnosedDiseaseDao diagnosedDiseaseDao = new DiagnosedDiseaseDao();
		diagnosedDiseaseDao.addDiagnosedDisease(diagDisease);

	}

// ---------------------------- Prescreve --------------------------------

	@Test
	public void insertValidPrescreve() {

		Prescreve prescreve = new Prescreve();
		Calendar data = Calendar.getInstance();

		prescreve.setMedicineName("Tylenol");
		prescreve.setDoctorCpf("12345678901");
		prescreve.setCRM(43436);
		prescreve.setPatientCpf("10987654321");
		prescreve.setDoctorName("Médico");
		prescreve.setPatientName("Patient");
		prescreve.setDate(data);
		prescreve.setTime(data);
		prescreve.setDose("5");
		prescreve.setPeriod(data);
		prescreve.setFrequency("3");

		Schedule sched = new Schedule();
		sched.setDoctorCpf("12345678901");
		sched.setPatientCpf("10987654321");
		sched.setDateTime(data);
		sched.setType("refazer");
		sched.setComment(" ");

		ScheduleDao schedDao = new ScheduleDao();
		try {
			schedDao.addSchedule(sched);
		}

		catch(SQLException e){
			throw new RuntimeException(e);
		}

		PrescreveDao prescreveDao = new PrescreveDao();
		prescreveDao.addPrescreve(prescreve);

	}

	@Test(expected=RuntimeException.class)
	public void insertInvalidMedicineNamePrescreve() {

		Prescreve prescreve = new Prescreve();
		Calendar data = Calendar.getInstance();

		prescreve.setMedicineName("");
		prescreve.setDoctorCpf("12345678901");
		prescreve.setCRM(43436);
		prescreve.setPatientCpf("10987654321");
		prescreve.setDoctorName("Médico");
		prescreve.setPatientName("Patient");
		prescreve.setDate(data);
		prescreve.setTime(data);
		prescreve.setDose("5");
		prescreve.setPeriod(data);
		prescreve.setFrequency("3");

		Schedule sched = new Schedule();
		sched.setDoctorCpf("12345678901");
		sched.setPatientCpf("10987654321");
		sched.setDateTime(data);
		sched.setType("refazer");
		sched.setComment(" ");

		ScheduleDao schedDao = new ScheduleDao();
		try {
			schedDao.addSchedule(sched);
		}

		catch(SQLException e){
			throw new RuntimeException(e);
		}

		PrescreveDao prescreveDao = new PrescreveDao();
		prescreveDao.addPrescreve(prescreve);

	}

	@Test(expected=RuntimeException.class)
	public void insertInvalidDoctorCpfPrescreve() {

		Prescreve prescreve = new Prescreve();
		Calendar data = Calendar.getInstance();

		prescreve.setMedicineName("Tylenol");
		prescreve.setDoctorCpf(null);
		prescreve.setCRM(43436);
		prescreve.setPatientCpf("10987654321");
		prescreve.setDoctorName("Médico");
		prescreve.setPatientName("Patient");
		prescreve.setDate(data);
		prescreve.setTime(data);
		prescreve.setDose("5");
		prescreve.setPeriod(data);
		prescreve.setFrequency("3");

		Schedule sched = new Schedule();
		sched.setDoctorCpf("12345678901");
		sched.setPatientCpf("10987654321");
		sched.setDateTime(data);
		sched.setType("refazer");
		sched.setComment(" ");

		ScheduleDao schedDao = new ScheduleDao();
		try {
			schedDao.addSchedule(sched);
		}

		catch(SQLException e){
			throw new RuntimeException(e);
		}

		PrescreveDao prescreveDao = new PrescreveDao();
		prescreveDao.addPrescreve(prescreve);

	}

	@Test(expected=RuntimeException.class)
	public void insertInvalidPatientCpfPrescreve() {

		Prescreve prescreve = new Prescreve();
		Calendar data = Calendar.getInstance();

		prescreve.setMedicineName("Tylenol");
		prescreve.setDoctorCpf("12345678901");
		prescreve.setCRM(43436);
		prescreve.setPatientCpf("");
		prescreve.setDoctorName("Médico");
		prescreve.setPatientName("Patient");
		prescreve.setDate(data);
		prescreve.setTime(data);
		prescreve.setDose("5");
		prescreve.setPeriod(data);
		prescreve.setFrequency("3");

		Schedule sched = new Schedule();
		sched.setDoctorCpf("12345678901");
		sched.setPatientCpf("10987654321");
		sched.setDateTime(data);
		sched.setType("refazer");
		sched.setComment(" ");

		ScheduleDao schedDao = new ScheduleDao();
		try {
			schedDao.addSchedule(sched);
		}

		catch(SQLException e){
			throw new RuntimeException(e);
		}

		PrescreveDao prescreveDao = new PrescreveDao();
		prescreveDao.addPrescreve(prescreve);

	}

	@Test(expected=RuntimeException.class)
	public void insertInvalidSchedulePrescreve() {

		Prescreve prescreve = new Prescreve();
		Calendar data = Calendar.getInstance();

		prescreve.setMedicineName("Tylenol");
		prescreve.setDoctorCpf("12345678901");
		prescreve.setCRM(43436);
		prescreve.setPatientCpf("10987654321");
		prescreve.setDoctorName("Médico");
		prescreve.setPatientName("Patient");
		prescreve.setDate(data);
		prescreve.setTime(data);
		prescreve.setDose("5");
		prescreve.setPeriod(data);
		prescreve.setFrequency("3");

		Schedule sched = new Schedule();
		sched.setDoctorCpf("00000000000");
		sched.setPatientCpf("10987654321");
		sched.setDateTime(Calendar.getInstance());
		sched.setType("refazer");
		sched.setComment(" ");

		ScheduleDao schedDao = new ScheduleDao();
		try {
			schedDao.addSchedule(sched);
		}

		catch(SQLException e){
			throw new RuntimeException(e);
		}

		PrescreveDao prescreveDao = new PrescreveDao();
		prescreveDao.addPrescreve(prescreve);

	}

	@Test(expected=RuntimeException.class)
	public void insertEmptyPrescreve() {

		Prescreve prescreve = new Prescreve();
		Calendar data = Calendar.getInstance();

		Schedule sched = new Schedule();
		sched.setDoctorCpf("12345678901");
		sched.setPatientCpf("10987654321");
		sched.setDateTime(data);
		sched.setType("refazer");
		sched.setComment(" ");

		ScheduleDao schedDao = new ScheduleDao();
		try {
			schedDao.addSchedule(sched);
		}

		catch(SQLException e){
			throw new RuntimeException(e);
		}

		PrescreveDao prescreveDao = new PrescreveDao();
		prescreveDao.addPrescreve(prescreve);

	}

// ---------------------------- User --------------------------------

	@Test
	public void insertValidUser() {

		Person person = new Person();
		person.setName("Nome");
		person.setEmail("mac439@ime.usp.br");
		person.setAddress("R. do Matão, 1010");
		person.setBirthDate(Calendar.getInstance());
		person.setRg("115345151");
		person.setCpf("11534515124");

		PersonDao personDao = new PersonDao();
		personDao.addUser(person);
	}

	@Test(expected=RuntimeException.class)
	public void insertDuplicatedUser() {

		Person person = new Person();
		person.setName("Outro Nome");
		person.setEmail("mac439@ime.usp.br");
		person.setAddress("R. do Matão, 1010");
		person.setBirthDate(Calendar.getInstance());
		person.setRg("115345151");
		person.setCpf("11534515124");

		PersonDao personDao = new PersonDao();
		personDao.addUser(person);
	}

	@Test
	public void insertSameNameUser() {

		Person person = new Person();
		person.setName("Outro Nome");
		person.setEmail("mac439@ime.usp.br");
		person.setAddress("R. do Matão, 1010");
		person.setBirthDate(Calendar.getInstance());
		person.setRg("115345151");
		person.setCpf("11534515125");

		PersonDao personDao = new PersonDao();
		personDao.addUser(person);
	}

	@Test
	public void editValidUser() {

		Person person = new Person();
		person.setName("Outro Nome");
		person.setEmail("mac439@ime.usp.br");
		person.setAddress("R. do Matão, 1010");
		person.setBirthDate(Calendar.getInstance());
		person.setRg("115345151");
		person.setCpf("11534515125");

		PersonDao personDao = new PersonDao();
		personDao.updateUser(person);
	}

	// Esse teste (abaixo) deveria dar Runtime Error quando a consulta não
	// existe, mas como usamos o update na hora de alterar a consulta,
	// se ela não existir ele acaba criando um novo.

	@Test(expected=RuntimeException.class)
	public void editInvalidCpfUser() {

		Person person = new Person();
		person.setName("Outro Nome");
		person.setEmail("mac439@ime.usp.br");
		person.setAddress("R. do Matão, 1010");
		person.setBirthDate(Calendar.getInstance());
		person.setRg("115345151");
		person.setCpf(null);

		PersonDao personDao = new PersonDao();
		personDao.updateUser(person);
	}

	@Test
	public void editNameValidUser() {

		Person person = new Person();
		person.setName("Mudei de Nome");
		person.setEmail("mac439@ime.usp.br");
		person.setAddress("R. do Matão, 1010");
		person.setBirthDate(Calendar.getInstance());
		person.setRg("115345151");
		person.setCpf("11534515125");

		PersonDao personDao = new PersonDao();
		personDao.updateUser(person);
	}

	// Esse teste (abaixo) deveria dar Runtime Error. Não faz sentido
	// uma pessoa não ter um nome e o database não deveria permitir isso.

	@Test(expected=RuntimeException.class)
	public void editInvalidNameValidUser() {

		Person person = new Person();
		person.setName("");
		person.setEmail("mac439@ime.usp.br");
		person.setAddress("R. do Matão, 1010");
		person.setBirthDate(Calendar.getInstance());
		person.setRg("115345151");
		person.setCpf("11534515125");

		PersonDao personDao = new PersonDao();
		personDao.updateUser(person);
	}

	// Esse teste (abaixo) deveria dar Runtime Error. Não faz sentido
	// uma pessoa ter um email vazio (se for pra ter um email vazio, 
	// a entrada deveria ser deletada) e o database não deveria permitir 
	// isso.

	@Test(expected=RuntimeException.class)
	public void editInvalidEmailValidUser() {

		Person person = new Person();
		person.setName("Nome");
		person.setEmail("");
		person.setAddress("R. do Matão, 1010");
		person.setBirthDate(Calendar.getInstance());
		person.setRg("115345151");
		person.setCpf("11534515125");

		PersonDao personDao = new PersonDao();
		personDao.updateUser(person);
	}	

	@Test
	public void editValidEmailValidUser() {

		Person person = new Person();
		person.setName("Nome");
		person.setEmail("abc@usp.br");
		person.setAddress("R. do Matão, 1010");
		person.setBirthDate(Calendar.getInstance());
		person.setRg("115345151");
		person.setCpf("11534515125");

		PersonDao personDao = new PersonDao();
		personDao.updateUser(person);
	}

	@AfterClass
	public static void setUpAfterClass() throws Exception {
		PersonDao personDao = new PersonDao();
		Person person = new Person();

		Long i;
		for (i = 11534515124L; i <= 11534515125L; i++){
			person.setCpf(Long.toString(i));
			personDao.removeUser(person);
		}		
	}

} 
