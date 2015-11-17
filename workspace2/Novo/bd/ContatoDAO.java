/* Exemplo adaptado de:  http://www.caelum.com.br/apostila-java-web/bancos-de-dados-e-jdbc/  */

package bd;

import java.sql.*;
import java.util.Calendar;
import modelo.*;
import java.util.List;
import java.util.ArrayList;

public class ContatoDAO {

	// a conexão com o banco de dados
	private Connection conexao;

	public ContatoDAO() {
		this.conexao = FabricaDeConexao.obterInstancia().obterConexao();
		// Statement statement = this.conexao.createStatement(); 
		// try { 
		// 	statement.execute("set search_path to '" + "imecare" + "'"); 
		// }
		// finally { 
		// 	statement.close(); 
		// }
	}

	public void addUser(Person person){
		String sql = "insert into pessoa "
				+ "(cpf,rg,	nome, dt_nascimento)" + " values (?,?,?,?)";
		
		try {
			// prepared statement para inserção
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// seta os valores

			stmt.setString(1, person.getCpf());
			stmt.setString(2, person.getRg());
			stmt.setString(3, person.getName());
			stmt.setDate(4, new Date(person.getBirthDate().getTimeInMillis()));
			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// A SQLException é "encapsulada" em uma RuntimeException
			// para desacoplar o código da API de JDBC
			throw new RuntimeException(e);
		}	
		
		addEmail(person);	
	}

	public void updateUser(Person person){

		String sql = "update pessoa set nome=?, rg=?, dt_nascimento=?"
				+ " where cpf=?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, person.getName());
			stmt.setString(2, person.getRg());
			stmt.setDate(3, new Date(person.getBirthDate().getTimeInMillis()));
			stmt.setString(4, person.getCpf());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		updateEmail(person);
	}

	public Person getUser(String cpf){

		try {
			Person person = null;
			
			PreparedStatement stmt = conexao.prepareStatement("select * "
					+ "from pessoa where cpf=?");
			stmt.setString(1, cpf);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				person = new Person();

				person.setCpf(cpf);
				person.setName(rs.getString("nome"));
				person.setAddress(rs.getString("endereco"));
				person.setEmail(rs.getString("email"));

				Calendar date = Calendar.getInstance();
				date.setTime(rs.getDate("dt_nascimento"));
				person.setBirthDate(date);

			}
			rs.close();
			stmt.close();
			return person;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void removeUser(Person person) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("delete "
					+ "from pessoa where cpf=?");
			stmt.setString(1, person.getCpf());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void addEmail(Person person){
		String sql = "insert into email "
				+ "(cpf,email)" + " values (?,?)";
		
		try {
			// prepared statement para inserção
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// seta os valores

			stmt.setString(1, person.getCpf());		
			stmt.setString(2, person.getEmail());
			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// A SQLException é "encapsulada" em uma RuntimeException
			// para desacoplar o código da API de JDBC
			throw new RuntimeException(e);
		}
		
	}

	public void updateEmail(Person person){

		String sql = "update email set email=?"
				+ " where cpf=?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, person.getEmail());
			stmt.setString(2, person.getCpf());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public void removeEmail(Person person) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("delete "
					+ "from email where cpf=?");
			stmt.setString(1, person.getCpf());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void addDoctor(Doctor doctor){
			
		addUser(doctor);

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

		updateUser(doctor);

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

	public void addPaciente(Paciente paciente){
		String sql = "insert into paciente "
				+ "(cpf, tipo_sanguineo)" + " values (?,?)";
		
		addUser(paciente);	

		try {
			// prepared statement para inserção
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, paciente.getCpf());	
			stmt.setString(2, paciente.getBloodType());			
			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
		
	}	

	// Obs: Pacientes podiam ter alergias
	public void updatePaciente(Paciente paciente){

		updateUser(paciente);

		String sql = "update paciente set tipo_sanguineo=? where cpf=?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, paciente.getBloodType());
			stmt.setString(2, paciente.getCpf());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	
	}

	public void removePaciente(Paciente paciente) {

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

	public void addMedicine(Medicine medicine){
		String sql = "insert into medicamento "
				+ "(tarja, nome)" + " values (?,?)";
		
		try {
			// prepared statement para inserção
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, medicine.getTarja());	
			stmt.setString(2, medicine.getName());			
			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
		
	}

	// Obs: Medicamentos não tem substâncias no DB
	public void updateMedicine(Medicine medicine){

		String sql = "update medicamento set tarja=? where nome=?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, medicine.getTarja());
			stmt.setString(2, medicine.getName());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void removeMedicine(Medicine medicine) {

		try {
			PreparedStatement stmt = conexao.prepareStatement("delete "
					+ "from medicamento where nome=?");
			stmt.setString(1, medicine.getName());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void addDoenca(Doenca doenca){
		String sql = "insert into doenca "
				+ "(CID, nome)" + " values (?,?)";
		
		try {
			// prepared statement para inserção
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, doenca.getCid());	
			stmt.setString(2, doenca.getName());			
			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}

	public void removeDoenca(Doenca doenca) {

		try {
			PreparedStatement stmt = conexao.prepareStatement("delete "
					+ "from medicamento where cid=?");
			stmt.setString(1, doenca.getCid());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void addSympton(Sympton sympton){
		String sql = "insert into sintoma "
				+ "(nome, descricao)" + " values (?,?)";
		
		try {
			// prepared statement para inserção
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// seta os valores

			stmt.setString(1, sympton.getName());
			stmt.setString(2, sympton.getDescription());
			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// A SQLException é "encapsulada" em uma RuntimeException
			// para desacoplar o código da API de JDBC
			throw new RuntimeException(e);
		}	
		
	}

	public void updateSympton(Sympton sympton){

		String sql = "update sympton set nome=?, descricao=?"
				+ " where nome=?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, sympton.getName());
			stmt.setString(2, sympton.getDescription());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Sympton getSympton(String name){

		try {
			Sympton sympton = null;
			
			PreparedStatement stmt = conexao.prepareStatement("select * "
					+ "from sintoma where nome=?");
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				sympton = new Sympton();

				sympton.setName(rs.getString("nome"));
				sympton.setDescription(rs.getString("descricao"));
				

			}
			rs.close();
			stmt.close();
			return sympton;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void removeSympton(Sympton sympton) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("delete "
					+ "from sintoma where nome=?");
			stmt.setString(1, sympton.getName());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public void addExam(Exam exam){
		String sql = "insert into exame "
				+ "(codigo_anvisa,nome,descricao)" + " values (?,?,?)";
		
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);

			stmt.setInt(1, exam.getAnvisaCode());
			stmt.setString(2, exam.getName());
			stmt.setString(3, exam.getDescription());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}

	public void updateExam(Exam exam){

		String sql = "update exame set nome=?, descricao=? where codigo_anvisa=?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, exam.getName());
			stmt.setString(2, exam.getDescription());
			stmt.setInt(3, exam.getAnvisaCode());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public void removeExam(Exam exam){

		try {
			PreparedStatement stmt = conexao.prepareStatement("delete "
					+ "from exame where codigo_anvisa=?");
			stmt.setInt(1, exam.getAnvisaCode());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public Exam getExam(Integer code){

		try {
			Exam exam = null;
			
			PreparedStatement stmt = conexao.prepareStatement("select * "
					+ "from exame where codigo_anvisa=?");
			stmt.setInt(1, code);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				exam = new Exam();

				exam.setName(rs.getString("nome"));
				exam.setDescription(rs.getString("descricao"));
				exam.setAnvisaCode(code);
				

			}
			rs.close();
			stmt.close();
			return exam;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
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

	public void cleanTable(String table){
		String sql = "delete from " + table;

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}

	public void addSchedule(Schedule schedule){

		String sql = "insert into atendimento "
				+ "(CPF_Medico, CPF_Paciente, data, horario, tipo_atendimento, comentario)"
				+ " values (?,?,?,?,?,?)";
		
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);

			stmt.setString(1, schedule.getDoctorCpf());
			stmt.setString(2, schedule.getPacienteCpf());
			stmt.setDate(3, new Date(schedule.getDateTime().getTimeInMillis()));
			stmt.setTime(4, new Time(schedule.getDateTime().getTimeInMillis()));
			stmt.setString(5, schedule.getType());
			stmt.setString(6, schedule.getComment());

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// A SQLException é "encapsulada" em uma RuntimeException
			// para desacoplar o código da API de JDBC
			throw new RuntimeException(e);
		}	
			
	}

	public void updateSchedule(Schedule schedule){


		String sql = "update atendimento set data=? horario=? tipo_atendimento=? comentario=?"
					+ "where CPF_Paciente=? CPF_Medico=?";
		
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);

			stmt.setDate(1, new Date(schedule.getDateTime().getTimeInMillis()));
			stmt.setTime(2, new Time(schedule.getDateTime().getTimeInMillis()));
			stmt.setString(3, schedule.getType());
			stmt.setString(4, schedule.getComment());
			stmt.setString(5, schedule.getDoctorCpf());
			stmt.setString(6, schedule.getPacienteCpf());

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// A SQLException é "encapsulada" em uma RuntimeException
			// para desacoplar o código da API de JDBC
			throw new RuntimeException(e);
		}	
			
	}

	public void removeSchedule(Schedule schedule){

		try {
			PreparedStatement stmt = conexao.prepareStatement("delete "
					+ "from exame where CPF_Paciente=? and CPF_Medico=? and data=? and hora=?");
			stmt.setString(1, schedule.getPacienteCpf());
			stmt.setString(2, schedule.getDoctorCpf());
			stmt.setDate(3, new Date(schedule.getDateTime().getTimeInMillis()));
			stmt.setTime(4, new Time(schedule.getDateTime().getTimeInMillis()));
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}

//	public void remove(Contato contato) {
//		try {
//			PreparedStatement stmt = conexao.prepareStatement("delete "
//					+ "from contatos where id=?");
//			stmt.setLong(1, contato.getId());
//			stmt.execute();
//			stmt.close();
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	public Contato obter(int id) {
//		try {
//			Contato contato = null;
//			
//			PreparedStatement stmt = conexao.prepareStatement("select * "
//					+ "from contatos where id=?");
//			stmt.setLong(1, id);
//			ResultSet rs = stmt.executeQuery();
//
//			if (rs.next()) {
//				contato = new Contato();
//				contato.setId(rs.getLong("id"));
//				contato.setNome(rs.getString("nome"));
//				contato.setEmail(rs.getString("email"));
//				contato.setEndereco(rs.getString("endereco"));
//
//				// montando a data através do Calendar
//				Calendar data = Calendar.getInstance();
//				data.setTime(rs.getDate("dataNascimento"));
//				contato.setDataNascimento(data);
//			}
//			rs.close();
//			stmt.close();
//			return contato;
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	public List<Contato> obterLista() {
//		try {
//			List<Contato> contatos = new ArrayList<Contato>();
//			PreparedStatement stmt = this.conexao
//					.prepareStatement("select * from contatos");
//			ResultSet rs = stmt.executeQuery();
//
//			while (rs.next()) {
//				// criando o objeto Contato
//				Contato contato = new Contato();
//				contato.setId(rs.getLong("id"));
//				contato.setNome(rs.getString("nome"));
//				contato.setEmail(rs.getString("email"));
//				contato.setEndereco(rs.getString("endereco"));
//
//				// montando a data através do Calendar
//				Calendar data = Calendar.getInstance();
//				data.setTime(rs.getDate("dataNascimento"));
//				contato.setDataNascimento(data);
//
//				// adicionando o objeto à lista
//				contatos.add(contato);
//			}
//			rs.close();
//			stmt.close();
//			return contatos;
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
//	}


/*To do functions
addPossui();
		addDoencaDiagnosticada()
		addPossui();
		addPrescreve();
		addPossui();*/