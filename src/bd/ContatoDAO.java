/* Exemplo adaptado de:  http://www.caelum.com.br/apostila-java-web/bancos-de-dados-e-jdbc/  */

package bd;

import java.sql.*;
import java.util.Calendar;
import modelo.*;

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
				+ "(cpf,rg,	nome, dt_nascimento,parentesco)" + " values (?,?,?,?,?)";
		
		try {
			// prepared statement para inserção
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// seta os valores

			stmt.setString(1, person.getCpf());
			stmt.setString(2, person.getRg());
			stmt.setString(3, person.getName());
			stmt.setDate(4, new Date(person.getBirthDate().getTimeInMillis()));
			stmt.setString(5, person.getParentesco());
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

		String sql = "update pessoa set nome=?, rg=?, dt_nascimento=?, parentesco=?"
				+ " where cpf=?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, person.getName());
			stmt.setString(2, person.getRg());
			stmt.setDate(3, new Date(person.getBirthDate().getTimeInMillis()));
			stmt.setString(4, person.getParentesco());
			stmt.setString(5, person.getCpf());
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

				person.setCpf(rs.getString("cpf"));
				person.setName(rs.getString("nome"));
				person.setAddress(rs.getString("endereco"));
				person.setEmail(rs.getString("email"));
				person.setParentesco(rs.getString("parentesco"));

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

		// removeEmail(person);
	}
	
	public void addEmail(Person person){
		String sql = "insert into email "
				+ "(cpf,nome,email)" + " values (?,?,?)";
		
		try {
			// prepared statement para inserção
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// seta os valores

			stmt.setString(1, person.getCpf());
			stmt.setString(2, person.getName());
			stmt.setString(3, person.getEmail());
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

	public void addPaciente(Paciente paciente){
		String sql = "insert into paciente "
				+ "(cpf, nome, tipo)" + " values (?,?,?)";
		
		addUser(paciente);	

		try {
			// prepared statement para inserção
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, paciente.getCpf());	
			stmt.setString(2, paciente.getName());	
			stmt.setString(3, paciente.getBloodType());			
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
