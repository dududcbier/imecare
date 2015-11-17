package bd;

import java.sql.*;
import java.util.*;

import modelo.Patient;
import modelo.Records;


public class RecordsDao {

	 private Connection conexao;
	 
	 public RecordsDao() {
		this.conexao = ConnectionFactory.obterInstancia().obterConexao();
	 }
	 
	 public List<Records> listRecord() {
			
	
		 try {
				List<Records> record = new ArrayList<Records>();
				PreparedStatement stmt = this.conexao
						.prepareStatement("select * from prontuario");
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
}