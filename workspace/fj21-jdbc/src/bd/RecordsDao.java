package bd;

import java.sql.*;
import java.util.*;

import modelo.*;
import bd.*;


public class RecordsDao {

	 private Connection conexao;
	 
	 public RecordsDao() {
		this.conexao = ConnectionFactory.obterInstancia().obterConexao();
	 }
	 
	 public List<Records> listRecord(String cpf) {
			
	
		 try {
				List<Records> record = new ArrayList<Records>();
				PreparedStatement stmt = this.conexao
						.prepareStatement("select * from prontuario where cpf=" + cpf);
				ResultSet rs = stmt.executeQuery();
	
	
				while (rs.next()) {
					// criando o objeto Contato
					Records record1 = new Records();
					record1.setType(rs.getString("tipo_sanguineo"));
					record1.setCpf(rs.getString("cpf"));
					record1.setCid(rs.getString("CID"));
					record1.setDiseaseName(rs.getString("nome_doenca"));
					record1.setSymptonName(rs.getString("nome_sintoma"));
					record1.setSymptonDescription(rs.getString("descricao_sintoma"));
					record1.setMedicineName(rs.getString("nome_medicamento"));
					record1.setMedicalStripe(rs.getString("tarja"));
					record1.setAnvisaCode(rs.getInt("codigo_anvisa"));
					record1.setExamName(rs.getString("nome_exame"));
					record1.setExamDescription(rs.getString("descricao_exame"));
	
					// adicionando o objeto à lista
					record.add(record1);
				}
				rs.close();
				stmt.close();
				return record;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

	public void addRecords(Records record) {
			
		Exam exam = new Exam();
		exam.setAnvisaCode(record.getAnvisaCode());
		exam.setName(record.getExamName());
		exam.setDescription(record.getExamDescription());

		ExamDao examDao = new ExamDao();
		try {
			examDao.addExam(exam);
		} catch (Exception e) { }

		Medicine medicine = new Medicine();
		medicine.setName(record.getMedicineName());
		medicine.setTarja(record.getMedicalStripe());

		MedicineDao medicineDao = new MedicineDao();
		try {
			medicineDao.addMedicine(medicine);
		} catch (Exception e) { }

		Sympton sympton = new Sympton();
		sympton.setName(record.getSymptonName());
		sympton.setDescription(record.getSymptonDescription());

		SymptonDao symptonDao = new SymptonDao();
		try {
			symptonDao.addSympton(sympton);
		} catch (Exception e) { }

		Doenca doenca = new Doenca();
		doenca.setName(record.getDiseaseName());
		doenca.setCid(record.getCid());

		DoencaDao doencaDao = new DoencaDao();
		try {
			doencaDao.addDoenca(doenca);
		} catch (Exception e) { }

		Patient patient = new Patient();
		patient.setName(record.getNamePatient());
		patient.setCpf(record.getCpf());
		patient.setBloodType(record.getType());

		PatientDao patientDao = new PatientDao();
		try {
			patientDao.addPatient(patient);
		} catch (Exception e) { }

	    DiagnosedDisease diagnosedDisease = new DiagnosedDisease();
	    diagnosedDisease.setCid(doenca.getCid());
	    diagnosedDisease.setPatientCpf(patient.getCpf());
	    diagnosedDisease.setDate(Calendar.getInstance());
	    diagnosedDisease.setTime(Calendar.getInstance());

	    DiagnosedDiseaseDao diagnosedDiseaseDao = new DiagnosedDiseaseDao();
	    try {
			diagnosedDiseaseDao.addDiagnosedDisease(diagnosedDisease);
		} catch (Exception e) { }

	    Possui possui = new Possui();
	    possui.setCid(doenca.getCid());
	    possui.setSymptonName(sympton.getName());

	    PossuiDao possuiDao = new PossuiDao();
	    try	{
	    	possuiDao.addPossui(possui);
	    } catch (Exception e){ }

	    Prescreve prescreve = new Prescreve();
	    prescreve.setMedicineName(medicine.getName());
	    prescreve.setPatientCpf(patient.getCpf());
	    prescreve.setDate(Calendar.getInstance());
	    prescreve.setTime(Calendar.getInstance());

	    PrescreveDao prescreveDao = new PrescreveDao();
	    try {
	    	prescreveDao.addPrescreve(prescreve);
	    } catch (Exception e) { }

		Realiza realiza = new Realiza();
		realiza.setAnvisaCode(exam.getAnvisaCode());
		realiza.setPatientCpf(patient.getCpf());

		RealizaDao realizaDao = new RealizaDao();
		try {
			realizaDao.addRealiza(realiza);
		} catch (Exception e) {}
	}
}