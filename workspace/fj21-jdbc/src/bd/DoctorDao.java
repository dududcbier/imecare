package bd;

import java.sql.*;

import modelo.Doctor;
import bd.PersonDao;

public class DoctorDao {

	private Connection conexao;
		 
	public DoctorDao() {
		this.conexao = ConnectionFactory.obterInstancia().obterConexao();
	}
	
	public void addDoctor(Doctor doctor){
		
		PersonDao person = new PersonDao();
				
		person.addUser(doctor);

		String sql = "insert into medico "
				+ "(crm, cpf)" + " values (?,?)";
		
		try {
			// prepared statement para inserção
			PreparedStatement stmt = conexao.prepareStatement(sql);			
			// executa
			stmt.setInt(1, doctor.getCrm());
			stmt.setString(2, doctor.getCpf());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
			
	}	

	public void updateDoctor(Doctor doctor){

		
		PersonDao person = new PersonDao();
		
		person.updateUser(doctor);

		String sql = "update medico set crm=? where cpf=?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, doctor.getCrm());
			stmt.setString(2, doctor.getCpf());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public void removeDoctor(Doctor doctor) {

		PersonDao person = new PersonDao();
		
		try {
			PreparedStatement stmt = conexao.prepareStatement("delete "
					+ "from medico where cpf=?");
			stmt.setString(1, doctor.getCpf());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		person.removeUser(doctor);
	}
	
}
