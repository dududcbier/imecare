package teste;

import java.sql.SQLException;
import bd.ContatoDAO;
import modelo.*;

public class TestaInsere {
	public static void main(String[] args) throws SQLException{
		// pronto para gravar
		Person person = new Person();
		person.setName("MAC439");
		person.setEmail("mac439@ime.usp.br");
		person.setAddress("R. do Matão, 1010");
		person.setBirthDate("2013-09-04");
		person.setRg("11111111");
		person.setCpf("22222222222");
		person.setParentesco("BFF");
		
		Doctor doctor = new Doctor();
		doctor.setName("Médico");
		doctor.setEmail("medico@medico.com.br");
		doctor.setAddress("Rua dos médicos, 123");
		doctor.setBirthDate("2013-09-04");
		doctor.setRg("123456789");
		doctor.setCpf("12345678901");
		doctor.setParentesco(" ");
		doctor.setCrm(43436);
		doctor.setEspecialidade("Neurologista");

		Paciente paciente = new Paciente();
		paciente.setName("Paciente");
		paciente.setEmail("paciente@paciente.com.br");
		paciente.setAddress("Rua dos pacientes, 123");
		paciente.setBirthDate("2013-09-04");
		paciente.setRg("987654321");
		paciente.setCpf("10987654321");
		paciente.setParentesco(" ");
		paciente.setTipoSanguineo("O+");

		Medicine medicine = new Medicine();
		medicine.setName("Tylenol");
		medicine.setTarja("preta");

		Doenca doenca = new Doenca();
		doenca.setName("Ebola");
	    doenca.setCid("4224");

		// grave nessa conexão!!!
		ContatoDAO dao = new ContatoDAO();
		 
		// método elegante
		dao.addUser(person);
		System.out.println("Novo person gravado com sucesso!");

		dao.addDoctor(doctor);
		System.out.println("Novo doctor gravado com sucesso!");

		dao.addPaciente(paciente);
		System.out.println("Novo paciente gravado com sucesso!");

		dao.addMedicine(medicine);
		System.out.println("Novo medicamento gravado com sucesso!");

		dao.addDoenca(doenca);
		System.out.println("Nova doenca gravado com sucesso!");

	}
}
