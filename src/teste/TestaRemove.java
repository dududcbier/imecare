package teste;

import java.sql.SQLException;
import bd.ContatoDAO;
import modelo.*;

public class TestaRemove {
	public static void main(String[] args) throws SQLException{
		
		Person person = new Person();
		person.setCpf("10987654321");

		Exam exam = new Exam();
		exam.setAnvisaCode(1);

		ContatoDAO dao = new ContatoDAO();
		 		 		 
		dao.removeUser(person);	 
		System.out.println("Contato foi removido com sucesso!");

		dao.removeExam(exam);
		System.out.println("Exame foi removido com sucesso!");
		
	}
}

