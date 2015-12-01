package teste;

import static org.junit.Assert.assertEquals;

import org.junit.*;
import bd.DoctorDao;
import modelo.Doctor;
import bd.PersonDao;
import modelo.Person;
import bd.PatientDao;
import modelo.Patient;
import java.sql.*;
import java.util.*;

public class TestRegisterStateChart {

	DoctorDao doctorDao = new DoctorDao();

	// User já foi testado em Test Profile State Chart
	// Patient já foi testado em Test Patient

	@Test
	public void insertValidDoctor() {
			
		Doctor doctor = new Doctor();
		doctor.setName("Doctor Who");
		doctor.setEmail("medico@medico.com.br");
		doctor.setAddress("Rua dos médicos, 123");
		doctor.setBirthDate(Calendar.getInstance());
		doctor.setRg("333222111");
		doctor.setCpf("33322211100");
		doctor.setCrm(43436);
		doctor.setEspecialidade("Doctor");

		doctorDao.addDoctor(doctor);

	}

	@Test(expected=RuntimeException.class)
	public void insertDuplicatedDoctor() {
			
		Doctor doctor = new Doctor();
		doctor.setName("Doc");
		doctor.setEmail("medico@medico.com.br");
		doctor.setAddress("Rua dos médicos, 123");
		doctor.setBirthDate(Calendar.getInstance());
		doctor.setRg("333222111");
		doctor.setCpf("33322211100");
		doctor.setCrm(43436);
		doctor.setEspecialidade("Doctor");

		doctorDao.addDoctor(doctor);
		doctorDao.addDoctor(doctor);

	}

	@Test(expected=RuntimeException.class)
	public void insertInvalidEmptyCpfDoctor() {
			
		Doctor doctor = new Doctor();
		doctor.setName("Doc");
		doctor.setEmail("medico@medico.com.br");
		doctor.setAddress("Rua dos médicos, 123");
		doctor.setBirthDate(Calendar.getInstance());
		doctor.setRg("333222111");
		doctor.setCpf(null);
		doctor.setCrm(43436);
		doctor.setEspecialidade("Doctor");

		doctorDao.addDoctor(doctor);

	}

	@After
	public void tearDown() {
		Doctor doctor = new Doctor();
		Long i = 33322211100L;
		doctor.setCpf(Long.toString(i));
		doctorDao.removeDoctor(doctor);
	}

} 
