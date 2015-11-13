//package teste;
//
//import java.sql.SQLException;
//import java.util.List;
//
//import modelo.Contato;
//import bd.ContatoDAO;
//
//public class TestaLista {
//	public static void main(String[] args) throws SQLException{
//		ContatoDAO dao = new ContatoDAO();
//		
//		List<Contato> contatos = dao.obterLista();
//		
//		for (Contato contato : contatos) {
//			  System.out.println("ID: " + contato.getId());
//			  System.out.println("Nome: " + contato.getNome());
//			  System.out.println("Email: " + contato.getEmail());
//			  System.out.println("Endereço: " + contato.getEndereco());
//			  System.out.println("Data de Nascimento: " + 
//			              contato.getDataNascimento().getTime() + "\n");
//			}
//	}
//}
