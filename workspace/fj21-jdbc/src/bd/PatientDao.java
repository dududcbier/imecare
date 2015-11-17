package bd;

import java.sql.*;

import modelo.Patient;
import bd.PersonDao;

public class PatientDao {

	private Connection conexao;
	 
	public PatientDao() {
		this.conexao = ConnectionFactory.obterInstancia().obterConexao();
	}

	public void addPatient(Patient patient){
		String sql = "insert into paciente "
				+ "(cpf, tipo_sanguineo)" + " values (?,?)";

		PersonDao personDao = new PersonDao();
		personDao.addUser(patient);	

		try {
			// prepared statement para inserção
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, patient.getCpf());	
			stmt.setString(2, patient.getBloodType());			
			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
		
	}	
	
	public void updatePatient(Patient patient){

		PersonDao person = new PersonDao();
		
		person.updateUser(patient);

		String sql = "update paciente set tipo_sanguineo=? where cpf=?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, patient.getBloodType());
			stmt.setString(2, patient.getCpf());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void removePatient(Patient patient) {

        PersonDao person = new PersonDao();
			
		try {
			PreparedStatement stmt = conexao.prepareStatement("delete "
					+ "from paciente where cpf=?");
			stmt.setString(1, patient.getCpf());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		person.removeUser(patient);
	}
	
}
