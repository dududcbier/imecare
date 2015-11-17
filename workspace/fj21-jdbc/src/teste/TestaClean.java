package teste;

import java.sql.SQLException;
import bd.ContatoDao;

public class TestaClean {
	public static void main(String[] args) throws SQLException{

		ContatoDao dao = new ContatoDao();
		dao.cleanTable("pessoa");
		dao.cleanTable("medico");
		dao.cleanTable("email");
		dao.cleanTable("medicamento");
		dao.cleanTable("doenca");
		dao.cleanTable("sintoma");
		dao.cleanTable("exame");
		dao.cleanTable("atendimento");
	}
}