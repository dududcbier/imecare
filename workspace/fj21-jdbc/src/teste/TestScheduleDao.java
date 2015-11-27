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
		// assert statements
		// assertEquals("Result must be -1 (SQL Exception)", -1, insertSchedule(sched));
	}
} 
