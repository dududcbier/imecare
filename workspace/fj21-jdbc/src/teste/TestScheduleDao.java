package teste;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import bd.ScheduleDao;
import modelo.Schedule;
import java.sql.*;
import java.util.*;

public class TestScheduleDao {

	private int insertSchedule(Schedule schedule){
		ScheduleDao schedDao = new ScheduleDao();

		try {
			schedDao.addSchedule(schedule);
		}

		catch(SQLException e){
			return -1;
		}
		return 0;
	}

	@Test(expected=RuntimeException.class)
	public void insertPatientEmptyCPF() {

		Schedule sched = new Schedule();
		sched.setDoctorCpf("12345678901");
		sched.setPatientCpf("");
		sched.setDateTime(Calendar.getInstance());
		sched.setType("refazer");
		sched.setComment("Simpático");

		insertSchedule(sched);
	}

	@Test(expected=RuntimeException.class)
	public void insertDoctorEmptyCPF() {

		Schedule sched = new Schedule();
		sched.setDoctorCpf("");
		sched.setPatientCpf("10987654321");
		sched.setDateTime(Calendar.getInstance());
		sched.setType("refazer");
		sched.setComment("Simpático");

		insertSchedule(sched);
	}

	@Test
	public void insertValidSchedule() {

		Schedule sched = new Schedule();
		sched.setDoctorCpf("12345678901");
		sched.setPatientCpf("10987654321");
		sched.setDateTime(Calendar.getInstance());
		sched.setType("refazer");
		sched.setComment("Simpático");

		assertEquals("Must be able to insert", 0, insertSchedule(sched));
	}

	@Test(expected=RuntimeException.class) 
	public void insertDuplicatedSchedule(){

		Schedule sched = new Schedule();
		sched.setDoctorCpf("12345678901");
		sched.setPatientCpf("10987654321");
		sched.setDateTime(Calendar.getInstance());
		sched.setType("refazer");
		sched.setComment("Simpático");

		assertEquals("Must be able to insert", 0, insertSchedule(sched));
		assertEquals("Must not be able to insert", -1, insertSchedule(sched));

	}

	@Test(expected=RuntimeException.class) 
	public void insertNullDate(){

		Schedule sched = new Schedule();
		sched.setDoctorCpf("12345678901");
		sched.setPatientCpf("10987654321");
		sched.setDateTime(null);
		sched.setType("refazer");
		sched.setComment("Simpático");

		assertEquals("Must not be able to insert", -1, insertSchedule(sched));

	}
} 
