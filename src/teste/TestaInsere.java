package teste;

import java.sql.SQLException;
import java.util.*;
import bd.ContatoDAO;
import modelo.*;

public class TestaInsere {
	public static void main(String[] args) throws SQLException{
		// pronto para gravar
		Person person = new Person();
		person.setName("MAC439");
		person.setEmail("mac439@ime.usp.br");
		person.setAddress("R. do Matão, 1010");
		person.setBirthDate(Calendar.getInstance());
		person.setRg("11111111");
		person.setCpf("22222222222");
		
		Doctor doctor = new Doctor();
		doctor.setName("Médico");
		doctor.setEmail("medico@medico.com.br");
		doctor.setAddress("Rua dos médicos, 123");
		doctor.setBirthDate(Calendar.getInstance());
		doctor.setRg("123456789");
		doctor.setCpf("12345678901");
		doctor.setCrm(43436);
		doctor.setEspecialidade("Neurologista");

		Paciente paciente = new Paciente();
		paciente.setName("Paciente");
		paciente.setEmail("paciente@paciente.com.br");
		paciente.setAddress("Rua dos pacientes, 123");
		paciente.setBirthDate(Calendar.getInstance());
		paciente.setRg("987654321");
		paciente.setCpf("10987654321");
		paciente.setBloodType("O+");

		Medicine medicine = new Medicine();
		medicine.setName("Tylenol");
		medicine.setTarja("preta");

		Doenca doenca = new Doenca();
		doenca.setName("Ebola");
	    doenca.setCid("4224");

	    Sympton sympton = new Sympton();
		sympton.setName("Febre");
	    sympton.setDescription("A temperatura chegou a 39 graus");

	    Exam exam = new Exam();
	    exam.setName("Ressonância Magnética");
	    exam.setDescription("Técnica que permite determinar propriedades de uma substância");
	    exam.setAnvisaCode(1);

        Records record = new Records();
	    record.setType("A+");
	    record.setNamePatient("João da Silva");
	    record.setCpf("12343123467");
	    record.setCid("31421");
		record.setDiseaseName("Diabetes");
		record.setSymptonName("Sede");
		record.setSymptonDescription("Sensação constante de sede");
		record.setMedicineName("Insulon");
		record.setMedicalStripe("vermelha");
		record.setAnvisaCode(2353);
		record.setExamName("Exame de sangue");
		record.setExamDescription("Será analisada a taxa de glicemia no sangue");

		Schedule sched = new Schedule();
		sched.setDoctorCpf("12345678901");
		sched.setPacienteCpf("10987654321");
		sched.setDateTime(Calendar.getInstance());
		sched.setType("refazer");
		sched.setComment("Simpático");


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

		dao.addSympton(sympton);
		System.out.println("Novo sintoma gravado com sucesso!");

		dao.addExam(exam);
		System.out.println("Novo exame gravado com sucesso!");

		dao.addSchedule(sched);
		System.out.println("Novo schedule gravado com sucesso!");


        List<Records> someList = new ArrayList<Records>();
        someList = dao.listRecord();
        for(Iterator<Records> i = someList.iterator(); i.hasNext();) {
  			Records item = i.next();
  			System.out.println(item);
        }
		System.out.println("Prontuário mostrado com sucesso!");
		System.out.println("Prontuário mostrado com sucesso!");



	}
}
