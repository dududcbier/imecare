package teste;

import java.sql.*;
import java.util.Calendar;

import bd.ContatoDao;
import modelo.Contato;

public class TestaInsere {

	public static void main(String[] args) throws SQLException{
		 // pronto para gravar
		 Contato contato = new Contato();
		 contato.setNome("MAC439");
		 contato.setEmail("mac439@ime.usp.br");
		 contato.setEndereco("R. do Matão, 1010");
		 contato.setDataNascimento(Calendar.getInstance());
		 
		 // grave nessa conexão!!!
		 ContatoDao dao = new ContatoDao();
		 
		 // método elegante
		 dao.adiciona(contato);
		 
		 System.out.println("Novo contato gravado com sucesso!");
	}
}