package teste;

import static org.junit.Assert.assertEquals;

import org.junit.*;
import bd.MedicineDao;
import modelo.Medicine;
import java.sql.*;
import java.util.*;


public class TestMedicine {

    private int insertMedicine(Medicine medicine) {
	
	MedicineDao medDao = new MedicineDao();

	try {
	    medDao.addMedicine(medicine);
	}

	catch(SQLException e) {
	    return -1;
	}

	return 0;
    }

    private int editMedicine(Medicine medicine) {
	
	MedicineDao medDao = new MedicineDao();

	try {
	    medDao.updateMedicine(medicine);
	}

	catch(SQLException e) {
	    return -1;
	}
	return 0;
    }

    private int deleteMedicine(Medicine medicine) {
	
	MedicineDao medDao = new MedicineDao();
	
	try {
	    medDao.removeMedicine(medicine);
	}
	
	catch(SQLException e) {
	    return -1;
	}
	return 0;
    }

    @Test(expected=RuntimeException.class)
    public void insertNullNameMedicine() {

		Medicine medicine = new Medicine();
		medicine.setTarja("preta");
		medicine.setName("");
		
		insertMedicine(medicine);
    }

    @Test
    public void insertValidMedicine() {

	Medicine medicine = new Medicine();
	medicine.setTarja(" ");
	medicine.setName("Omeprazol");
	
	assertEquals("Must be able to insert", 0, insertMedicine(medicine));
    }

    @Test(expected=RuntimeException.class)
    public void insertDuplicateMedicine() {

		Medicine medicine = new Medicine();
		medicine.setTarja("");
		medicine.setName("Tylenol");

		assertEquals("Must not be able to insert", -1, insertMedicine(medicine));

    }

    @Test
    public void deleteValidMedicine() {
	
	Medicine medicine = new Medicine();
	medicine.setTarja(" ");
	medicine.setName("Omeprazol");
	
	deleteMedicine(medicine);
	assertEquals("Must be able to delete", 0, deleteMedicine(medicine));
    }

    @Test(expected=RuntimeException.class)
    public void deleteInvalidMedicine() {
	
	Medicine medicine = new Medicine();
	medicine.setTarja("");
	medicine.setName("Atroveran");
	
	assertEquals("Must not be able to delete", -1, deleteMedicine(medicine));
	 
    }


}
