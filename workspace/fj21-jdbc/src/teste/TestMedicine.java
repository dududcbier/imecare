package teste;

import static org.junit.Assert.assertEquals;

import org.junit.*;
import bd.MedicineDao;
import modelo.Medicine;
import java.sql.*;
import java.util.*;


public class TestMedicine {

    @Test(expected=RuntimeException.class)
    public void insertNullNameMedicine() {

		Medicine medicine = new Medicine();
		medicine.setTarja("preta");
		medicine.setName("");

		MedicineDao medDao = new MedicineDao();		
		medDao.addMedicine(medicine);
    }

    @Test
    public void insertValidMedicine() {

		Medicine medicine = new Medicine();
		medicine.setTarja(" ");
		medicine.setName("Omeprazol");
		
		MedicineDao medDao = new MedicineDao();		
		medDao.addMedicine(medicine);
    }

    @Test(expected=RuntimeException.class)
    public void insertDuplicateMedicine() {

		Medicine medicine = new Medicine();
		medicine.setTarja("");
		medicine.setName("Tylenol");

		MedicineDao medDao = new MedicineDao();		
		medDao.addMedicine(medicine);
    }

    @Test
    public void deleteValidMedicine() {
	
		Medicine medicine = new Medicine();
		medicine.setTarja(" ");
		medicine.setName("Omeprazol");
		
		MedicineDao medDao = new MedicineDao();		
		medDao.removeMedicine(medicine);
    }

    @Test(expected=RuntimeException.class)
    public void deleteInvalidMedicine() {
	
	Medicine medicine = new Medicine();
	medicine.setTarja("");
	medicine.setName("Atroveran");
	
	MedicineDao medDao = new MedicineDao();		
	medDao.removeMedicine(medicine);
	 
    }


}
