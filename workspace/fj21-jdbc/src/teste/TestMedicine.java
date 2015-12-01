package teste;

import static org.junit.Assert.assertEquals;

import org.junit.*;
import bd.MedicineDao;
import modelo.Medicine;
import java.sql.*;
import java.util.*;


public class TestMedicine {
	
	private MedicineDao medDao = new MedicineDao();

    @Test(expected=RuntimeException.class)
    public void insertNullNameMedicine() {

		Medicine medicine = new Medicine();
		medicine.setTarja("preta");
		medicine.setName("");
		
		medDao.addMedicine(medicine);
    }

    @Test
    public void insertValidMedicine() {

		Medicine medicine = new Medicine();
		medicine.setTarja(" ");
		medicine.setName("Omeprazol");
				
		medDao.addMedicine(medicine);
    }

    @Test(expected=RuntimeException.class)
    public void insertDuplicateMedicine() {

		Medicine medicine = new Medicine();
		medicine.setTarja("");
		medicine.setName("Tylenol");
		
		medDao.addMedicine(medicine);
    }

    @Test
    public void deleteValidMedicine() {
	
		Medicine medicine = new Medicine();
		medicine.setTarja(" ");
		medicine.setName("Omeprazol");
				
		medDao.removeMedicine(medicine);
    }

    @Test(expected=RuntimeException.class)
    public void deleteInvalidMedicine() {
	
	Medicine medicine = new Medicine();
	medicine.setTarja("");
	medicine.setName("Atroveran");
		
	medDao.removeMedicine(medicine);
	 
    }


}
