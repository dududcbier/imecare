package teste;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import bd.ScheduleDao;
import bd.DiagnosedDiseaseDao;
import bd.PrescreveDao;
import bd.RecordsDao;
import modelo.Schedule;
import modelo.DiagnosedDisease;
import modelo.Prescreve;
import modelo.Records;
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

} 
