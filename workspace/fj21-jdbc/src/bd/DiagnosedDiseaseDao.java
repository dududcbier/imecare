package bd;

import java.sql.*;

import modelo.DiagnosedDisease;

public class DiagnosedDiseaseDao {

	private Connection conexao;
		 
	public DiagnosedDiseaseDao() {
	     this.conexao = ConnectionFactory.obterInstancia().obterConexao();
	}

	public void addDiagnosedDisease(DiagnosedDisease doenca){
		String sql = "insert into doenca_diagnosticada "
				+ "(CID, CPF_Medico, CPF_Paciente, data, horario)"
				+ " values (?,?,?,?)";
		try {
			// prepared statement para inserção
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, doenca.getCid());	
			stmt.setString(2, doenca.getDoctorCpf());
			stmt.setString(3, doenca.getPatientCpf());	
			stmt.setDate(4, new Date(doenca.getDate().getTimeInMillis()));
			stmt.setTime(5, new Time(doenca.getTime().getTimeInMillis()));			
			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}

	public void removeDiagnosedDisease(DiagnosedDisease doenca) {

		try {
			PreparedStatement stmt = conexao.prepareStatement("delete "
					+ "from doenca_diagnosticada where cid=? and CPF_Medico=? and CPF_Paciente=? and data=? and horario=?");
			stmt.setString(1, doenca.getCid());	
			stmt.setString(2, doenca.getDoctorCpf());
			stmt.setString(3, doenca.getPatientCpf());	
			stmt.setDate(4, new Date(doenca.getDate().getTimeInMillis()));
			stmt.setTime(5, new Time(doenca.getTime().getTimeInMillis()));			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
