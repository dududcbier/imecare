package bd;

import java.sql.*;
//import java.util.*;

import modelo.Prescreve;

public class PrescreveDao {
	
	private Connection conexao;
	 
	public PrescreveDao() {
		this.conexao = ConnectionFactory.obterInstancia().obterConexao();
	}

	
	
	public void addPrescreve(Prescreve prescreve){
		String sql = "insert into prescreve "
				+ "(nome_medicamento, CPF_Medico, CRM, CPF_Paciente, " +
				"nome_Medico, nome_Paciente, data, horario, dose, periodo, frequencia)" + 
				" values (?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			// prepared statement para inserção
			PreparedStatement stmt = conexao.prepareStatement(sql);
	
			// seta os valores

			stmt.setString(1, prescreve.getMedicineName());
			stmt.setString(2, prescreve.getDoctorCpf()); 
			stmt.setInt(3, prescreve.getCRM()); 
			stmt.setString(4, prescreve.getPatientCpf());
			stmt.setString(5, prescreve.getDoctorName());
			stmt.setString(6, prescreve.getPatientName());
			stmt.setDate(7, new Date(prescreve.getDate().getTimeInMillis()));
			stmt.setTime(8, new Time(prescreve.getTime().getTimeInMillis()));
			stmt.setString(9, prescreve.getDose());
			stmt.setTime(10, new Time(prescreve.getPeriod().getTimeInMillis()));
			stmt.setString(11, prescreve.getFrequency());
					
				
				
			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}		
	}
	
	
	
					
	public void removePrescreve (Prescreve prescreve) {
		try {
	
			
			PreparedStatement stmt = conexao.prepareStatement("delete "
					+ "from prescreve where cpf_Medico=? AND nome_Medico=? AND CRM=? AND CPF_Paciente=? " +
					"AND nome_Paciente=? AND Data=? AND Horario=? AND nome_medicamento=?");
			
			
			stmt.setString(1, prescreve.getMedicineName());
			stmt.setString(2, prescreve.getDoctorCpf()); 
			stmt.setInt(3, prescreve.getCRM()); 
			stmt.setString(4, prescreve.getPatientCpf());
			stmt.setString(5, prescreve.getDoctorName());
			stmt.setString(6, prescreve.getPatientName());
			stmt.setDate(7, new Date(prescreve.getDate().getTimeInMillis()));
			stmt.setTime(8, new Time(prescreve.getTime().getTimeInMillis()));
			stmt.setString(9, prescreve.getDose());
			stmt.setTime(10, new Time(prescreve.getPeriod().getTimeInMillis()));
			stmt.setString(11, prescreve.getFrequency());
					
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}


	}	
	
}