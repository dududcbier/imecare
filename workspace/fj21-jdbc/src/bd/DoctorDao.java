package bd;

import java.sql.*;

import modelo.Doctor;

public class DoctorDao {

	private Connection conexao;
		 
	public DoctorDao() {
		this.conexao = ConnectionFactory.obterInstancia().obterConexao();
	}
	
	public void addDoctor(Doctor doctor){
		
		UserDao.addUser(doctor);

		String sql = "insert into medico "
				+ "(crm, cpf, nome)" + " values (" + doctor.getCrm() + ",?,?)";
		
		try {
			// prepared statement para inserção
			PreparedStatement stmt = conexao.prepareStatement(sql);			
			// executa
			stmt.setString(1, doctor.getCpf());
			stmt.setString(2, doctor.getName());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
			
	}	

	public void updateDoctor(Doctor doctor){

		updateUser(doctor);

		String sql = "update medico set crm=" + doctor.getCrm() + ", nome=? where cpf=?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, doctor.getName());
			stmt.setString(2, doctor.getCpf());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public void removeDoctor(Doctor doctor) {

		try {
			PreparedStatement stmt = conexao.prepareStatement("delete "
					+ "from medico where cpf=?");
			stmt.setString(1, doctor.getCpf());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		removeUser(doctor);
	}
	
}
