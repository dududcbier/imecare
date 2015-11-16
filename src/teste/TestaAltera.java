package teste;

import java.sql.SQLException;
import java.util.Calendar;

import bd.ContatoDAO;
import modelo.*;

public class TestaAltera {
	public static void main(String[] args) throws SQLException {

		Person person = new Person();
		person.setName("Meu Nome");
		person.setEmail("novoEmail@ime.usp.br");
		person.setAddress("R. do Matão, 1010");
		person.setBirthDate(Calendar.getInstance());
		person.setRg("11111111");
		person.setCpf("22222222222");

		Doctor doctor = new Doctor();
		doctor.setName("Mééééééédico");
		doctor.setEmail("MUDEImeuEMAIL@gmail.com");
		doctor.setAddress("Rua dos médicos, 123");
		doctor.setBirthDate(Calendar.getInstance());
		doctor.setRg("123456789");
		doctor.setCpf("12345678901");
		doctor.setCrm(5464);
		doctor.setEspecialidade("Neurologista");

		Paciente paciente = new Paciente();
		paciente.setName("Senhor adoentado");
		paciente.setEmail("paciente@paciente.com.br");
		paciente.setAddress("Rua dos pacientes, 123");
		paciente.setBirthDate(Calendar.getInstance());
		paciente.setRg("987654321");
		paciente.setCpf("10987654321");
		paciente.setBloodType("A+");

		Medicine medicine = new Medicine();
		medicine.setName("Tylenol");
		medicine.setTarja("Vermelha");

		Exam exam = new Exam();
		exam.setName("Raio X");
		exam.setAnvisaCode(1);
		exam.setDescription("Raios x");

		ContatoDAO dao = new ContatoDAO();

		dao.updateUser(person);
		System.out.println("Novo person alterado com sucesso!");

		dao.updateDoctor(doctor);
		System.out.println("Novo doctor alterado com sucesso!");

		dao.updatePaciente(paciente);
		System.out.println("Novo paciente alterado com sucesso!");

		dao.updateMedicine(medicine);
		System.out.println("Novo medicamento alterado com sucesso!");

		dao.updateExam(exam);
		System.out.println("Novo exame alterado com sucesso!");


	}
}