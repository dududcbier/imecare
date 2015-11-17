package bd;

import java.sql.*;

import modelo.Patient;
import modelo.Person;

//Obs: Pacientes podiam ter alergias
public class PatientDao {

	private Connection conexao;
	 
	public PatientDao() {
		this.conexao = ConnectionFactory.obterInstancia().obterConexao();
	}
	
	public void updatePaciente(Patient paciente){

		updateUser(paciente);

		String sql = "update paciente set tipo=?, nome=? where cpf=?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, paciente.getBloodType());
			stmt.setString(2, paciente.getName());
			stmt.setString(3, paciente.getCpf());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void removePaciente(Patient paciente) {

		try {
			PreparedStatement stmt = conexao.prepareStatement("delete "
					+ "from paciente where cpf=?");
			stmt.setString(1, paciente.getCpf());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		removeUser(paciente);
	}
	
}